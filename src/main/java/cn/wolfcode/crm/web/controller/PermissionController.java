package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequiresPermissions(value={"权限列表", "permission:list"},logical= Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        model.addAttribute("pageInfo", permissionService.query(qo));
        return "permission/list";
    }

    /**
     * 加载系统权限
     * @return 重定向访问/permission/list.do
     */
    @RequiresPermissions(value={"加载权限", "permission:reload"},logical= Logical.OR)
    @RequestMapping("/reload")
    @ResponseBody
    public Object reload() {
        permissionService.reload();
        return new JSONResult();
    }
}
