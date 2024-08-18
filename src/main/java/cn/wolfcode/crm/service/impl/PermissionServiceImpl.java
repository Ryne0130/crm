package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

//部门对象的业务层实现
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper; //部门对象的mapper接口
    @Autowired
    private ApplicationContext ctx; //直接注入Spring容器

    public void reload() {
        //1:查询出当前系统拥有的权限表达式(排除重复)
        List<String> exps = permissionMapper.selectExpressions();
        //2:从Spring容器中取出所有贴有Controller注解的bean
        Collection<Object> ctrls = ctx.getBeansWithAnnotation(Controller.class).values();
        //3:循环迭代每一个对象
        for (Object ctrl : ctrls) {
            //判断当前对象是否是AOP代理对象
            if (AopUtils.isAopProxy(ctrl)) { //贴有Shiro注解的类会创建CGLIB的代理对象
                //4:获取该对象的字节码,从字节码中获取声明的方法
                Method[] ms = ctrl.getClass().getSuperclass().getDeclaredMethods();
                for (Method m : ms) {
                    //5:获取该方法上的权限注解
                    RequiresPermissions anno = m.getAnnotation(RequiresPermissions.class);
                    //6:权限注解对象不为null并且该表达式在数据库中不存在
                    if (anno != null && !exps.contains(anno.value()[1])) {
                        //7:从权限注解中获取权限的名称和表达式,封装成Permission对象
                        String name = anno.value()[0]; //权限名称
                        String expression = anno.value()[1]; //权限表达式
                        Permission p = new Permission(name, expression);
                        //8:把该权限保存到数据库
                        permissionMapper.insert(p);
                    }
                }
            }
        }
    }

    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> list = permissionMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }
}
