import cn.wolfcode.crm.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testList() {
        departmentService.listAll().forEach(System.out::println);
    }
}
