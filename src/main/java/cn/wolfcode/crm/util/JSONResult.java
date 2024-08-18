package cn.wolfcode.crm.util;

import lombok.Getter;

//用于返回JSON的工具
@Getter
public class JSONResult {
    //是否操作成功,默认成功
    private boolean success = true;
    //错误信息
    private String msg;

    //标记错误信息,只有在出错时才调用
    public JSONResult mark(String msg) {
        success = false;
        this.msg = msg;
        return this;
    }
}
