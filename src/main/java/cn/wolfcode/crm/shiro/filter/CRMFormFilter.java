package cn.wolfcode.crm.shiro.filter;

import cn.wolfcode.crm.util.JSONResult;
import cn.wolfcode.crm.util.JSONUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//CRM中使用的认证过滤器
@Component("formFilter")
public class CRMFormFilter extends FormAuthenticationFilter {
    //登录成功后执行的方法
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        //准备返回JSON
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().println(JSONUtil.toJSONString(new JSONResult()));
        HttpServletResponse res = (HttpServletResponse) response;
//        res.sendRedirect("");

        return false;
    }

    //登录失败后执行的方法
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            //准备返回JSON
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().println(JSONUtil.toJSONString(new JSONResult().mark("账号和密码不匹配")));
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
