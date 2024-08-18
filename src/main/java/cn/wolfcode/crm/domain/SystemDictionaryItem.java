package cn.wolfcode.crm.domain;

import cn.wolfcode.crm.util.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

//数据字典明细
@Setter@Getter@ToString
public class SystemDictionaryItem extends BaseDomain {
    private Long parentId; //字典目录ID

    private String title; //明细标题

    private Integer sequence; //序列

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("parentId", parentId);
        map.put("sequence", sequence);
        return JSONUtil.toJSONString(map);
    }
}