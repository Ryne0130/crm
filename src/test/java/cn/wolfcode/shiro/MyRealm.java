package cn.wolfcode.shiro;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//自定义的Realm
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取真实的认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        login(token.getPrincipal());
        //模拟数据库中查询到的数据
        String username = "admin";
        String password = "123";

        //跟token中的数据做对比
        if (!token.getPrincipal().equals(username)) {
            //token中的账号和数据库中的账号不一致,说明用户不存在的
            return null; //表示账号不存在
        }
        //封装当前主体的真实认证信息
        return new SimpleAuthenticationInfo(username, password, getName()); //返回真实认证信息
    }


    /**
     * 获取真实的授权信息
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给该授权对象添加信息
        //添加角色信息
        info.addRoles(Arrays.asList("role1", "role2"));
        //添加权限表达式
        info.addStringPermissions(Arrays.asList("user:save", "user:delete"));
        return info;
    }
}
