package cn.wolfcode.crm.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JSONUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJSONString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
