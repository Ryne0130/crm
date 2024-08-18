package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customerPool")
public class CustomerPoolController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") CustomerQueryObject qo, Model model) {
        //只查询状态为客户池的客户
        qo.setStatus(Customer.STATUS_POOL);
        model.addAttribute("pageInfo", customerService.query(qo));
        return "customer/pool";
    }
}
