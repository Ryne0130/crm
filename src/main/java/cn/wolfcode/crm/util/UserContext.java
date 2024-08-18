package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;

/*
    用户上下文工具类:作用把用户存入session
    从session中拿到登录的用户
*/
public abstract class UserContext {
    public static Employee getCurrentUser() {
        return (Employee) SecurityUtils.getSubject().getPrincipal();
    }
}
