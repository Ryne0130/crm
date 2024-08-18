package cn.wolfcode.crm.shiro.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//CRM项目专用的Realm
@Component("realm") //当前的Bean交给Spring管理,id=realm
public class CRMRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired //从容器中找到凭证匹配器注入进来
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 获取真实的认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken tk = (UsernamePasswordToken) token;

        //1:查询数据库获取真实的用户信息
        Employee emp = employeeService.getByName(tk.getUsername());
        if (emp == null) {
            //token中的账号和数据库中的账号不一致,说明用户不存在的
            return null; //表示账号不存在
        }
        //2:封装当前主体的真实认证信息
        return new SimpleAuthenticationInfo(emp, emp.getPassword(),
                ByteSource.Util.bytes(emp.getName()), getName()); //返回真实认证信息
    }

    /**
     * 获取真实的授权信息
     * @param principals
     * @return 返回当前主体的真实授权信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1:创建授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前主体的主身份
        Employee emp = (Employee) principals.getPrimaryPrincipal();
        //区分对待超管
        if (emp.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*");
        } else {
            //2:查询当前主体拥有的角色(String)
            List<String> roles = employeeService.listRoles(emp.getId());
            //3:查询当前主体拥有的权限表达式(String)
            List<String> permissions = employeeService.listPermissions(emp.getId());
            //4:把角色和权限表达式存入授权信息中返回
            info.addRoles(roles);
            info.addStringPermissions(permissions);
        }
        return info;
    }
}
