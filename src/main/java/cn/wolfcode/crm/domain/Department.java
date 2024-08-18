package cn.wolfcode.crm.domain;

import cn.wolfcode.crm.util.JSONUtil;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter@Getter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseDomain {
    private String name;

    private String sn;

    /**
     * 返回当前对象的JSON字符串
     * 部门对象上多了一个叫json的属性
     * @return
     */
    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("sn", sn);
        return JSONUtil.toJSONString(map);
    }
}