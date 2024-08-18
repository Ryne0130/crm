package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {
        model.addAttribute("pageInfo", systemDictionaryService.query(qo));
        return "systemDictionary/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemDictionary entity) {
        systemDictionaryService.saveOrUpdate(entity);
        return new JSONResult();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        systemDictionaryService.delete(id);
        return new JSONResult();
    }
}
