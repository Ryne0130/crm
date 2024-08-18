package cn.wolfcode.crm.domain;

import cn.wolfcode.crm.util.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Setter@Getter@ToString
//数据字典目录
public class SystemDictionary extends BaseDomain {
    private String sn; //字典编码

    private String title; //字典标题

    private String intro; //字典介绍

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("sn", sn);
        map.put("intro", intro);
        return JSONUtil.toJSONString(map);
    }
}