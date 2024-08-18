package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.SystemDictionaryQueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {
    @Autowired
    private ISystemDictionaryService dictionaryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
        //查询所有的字典目录
        model.addAttribute("dictionaries", dictionaryService.listAll());
        //判断qo中的parentId大于0,查询对应的明细
        if (qo.getParentId() > 0) {
            qo.setOrderBy("sequence desc"); //按照序列降序排列
            model.addAttribute("pageInfo", dictionaryService.queryItem(qo));
        }
        return "systemDictionaryItem/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemDictionaryItem entity) {
        dictionaryService.saveOrUpdateItem(entity);
        return new JSONResult();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        dictionaryService.deleteItem(id);
        return new JSONResult();
    }
}
