package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.util.JSONResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        model.addAttribute("pageInfo", departmentService.query(qo));
        return "department/list";
    }

    //Shiro提供的权限表达式授权注解,修改逻辑关系为OR
    @RequiresPermissions(value={"保存/更新部门", "department:saveOrUpdate"},logical=Logical.OR)
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Department entity) {
        departmentService.saveOrUpdate(entity);
        return new JSONResult();
    }

    @RequiresPermissions(value={"删除部门", "department:delete"},logical=Logical.OR)
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        //调用业务方法处理请求
        departmentService.delete(id);
        return new JSONResult();
    }
}
