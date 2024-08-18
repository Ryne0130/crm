package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        model.addAttribute("pageInfo", roleService.query(qo));
        return "role/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            model.addAttribute("entity", roleService.get(id));
        }
        model.addAttribute("permissions", permissionService.listAll());
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role entity, Long[] ids) {
        roleService.saveOrUpdate(entity, ids);
        return "redirect:/role/list.do";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        roleService.delete(id);
        return new JSONResult();
    }
}
