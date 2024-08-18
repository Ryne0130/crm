package cn.wolfcode.crm.shiro.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

//CRM中集成第三方的Shiro标签的配置对象
public class CRMFreeMarkerConfigurer extends FreeMarkerConfigurer {

    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        /*
            注册第三方的Shiro标签(Freemarker使用的)
            就可以在Freemarker中使用了<@shrio:xxx>
         */
        getConfiguration().setSharedVariable("shiro", new ShiroTags());

    }
}
