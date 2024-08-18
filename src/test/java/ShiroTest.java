import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {
    @Test
    public void test() {
        //MD5加密
        Md5Hash hash = new Md5Hash("1","admin");
        System.out.println(hash);
    }

    @Test
    public void testLogin() {
        //1:创建一个基于ini的安全管理器工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2:从工厂获取到一个安全管理器对象
        SecurityManager manager = factory.getInstance();
        //3:设置该安全管理的运行环境
        SecurityUtils.setSecurityManager(manager);
        //4:通过框架的工具类获取到一个主体
        Subject subject = SecurityUtils.getSubject();
        //5:创建一个token,携带身份和凭证信息
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        System.out.println(subject.isAuthenticated()); //false
        //6:执行认证(登录)
        subject.login(token);
        //7:查看认证状态
        System.out.println(subject.isAuthenticated()); //true
        /*//8:退出登录
        subject.logout();
        System.out.println(subject.isAuthenticated()); //false*/
        //=======================================
        System.out.println("==========角色和权限的判断==========");
        //判断当前主体是否拥有role1的角色
        System.out.println("role1?=" + subject.hasRole("role1"));
        System.out.println("role3?=" + subject.hasRole("role3"));
        System.out.println("role1AndRole2=" + subject.hasAllRoles(Arrays.asList("role1","role2")));
        //判断当前主体是否拥有权限表达式
        System.out.println("user:delete?=" + subject.isPermitted("user:delete"));
        System.out.println("user:save?=" + subject.isPermitted("user:save"));
        System.out.println("user:list?=" + subject.isPermitted("user:list"));
        //==========================================
        //subject.checkRole("xxxx");
    }
}
