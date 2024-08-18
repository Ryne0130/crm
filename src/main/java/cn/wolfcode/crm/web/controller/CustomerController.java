package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JSONResult;
import cn.wolfcode.crm.util.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ISystemDictionaryService dictionaryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") CustomerQueryObject qo, Model model) {
        //设置数据权限,只有超管和经理才能看到所有客户,其他人员只能看到自己的客户
        Subject subject = SecurityUtils.getSubject();
        if (!(subject.hasRole("admin") || subject.hasRole("CLIENT_MGR"))) {
            //不是超管或经理,则设置当前营销人员的ID
            qo.setSellerId(UserContext.getCurrentUser().getId());
        }
        model.addAttribute("pageInfo", customerService.query(qo));
        //查询数据字典对应的内容
        model.addAttribute("jobs", dictionaryService.listItemsBySn("job"));
        model.addAttribute("sources", dictionaryService.listItemsBySn("source"));
        return "customer/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Customer entity) {
        customerService.saveOrUpdate(entity);
        return new JSONResult();
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Customer entity) {
        customerService.updateStatus(entity);
        return new JSONResult();
    }
}
