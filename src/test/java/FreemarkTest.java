import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkTest {
    @Test
    public void testFreemarker() throws Exception {
        //核心:模板+数据模型=视图输出
        // 1:创建Freemarker的核心配置对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // 2:指定模板文件从何处加载的数据源，这里设置成一个文件目录。
        cfg.setDirectoryForTemplateLoading(new File("E:/"));
        // 3:在该文件夹中读取一个模板
        Template tmp = cfg.getTemplate("1.html");
        // 4:准备一个数据模型(JavaBean)
        Map<String, Object> map = new HashMap<>();
        map.put("name", "逍遥");
        // 5:模板+数据 = 输出内容
        tmp.process(map, new FileWriter("d:/1.html"));
        /*
            注意:1:插入的占位符必须要和数据模型中的属性匹配
            注意:2:插入的属性不存在使用可以使用!设置该占位符的默认值
         */
    }
}
