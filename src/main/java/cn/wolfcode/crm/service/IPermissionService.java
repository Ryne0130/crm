package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

//部门对象的业务层接口
public interface IPermissionService {
    List<Permission> listAll();

    PageInfo<Permission> query(QueryObject qo);

    /**
     * 刷新系统拥有的权限
     */
    void reload();
}
