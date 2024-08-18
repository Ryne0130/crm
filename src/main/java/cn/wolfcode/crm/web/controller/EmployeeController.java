package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JSONResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/employee") //抽取路径前缀
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    //查询列表界面
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model) {
        model.addAttribute("pageInfo", employeeService.query(qo));
        model.addAttribute("depts", departmentService.listAll());
        return "employee/list";
    }

    @PostMapping("/register")
    public String enrollEmployee(@ModelAttribute("employee") Employee employee) {
        return employeeService.registerEmployee(employee);
    }

    @RequiresPermissions(value={"编辑员工", "employee:input"},logical= Logical.OR)
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            model.addAttribute("entity", employeeService.get(id));
        }
        //查询所有的部门显示在下拉列表中
        model.addAttribute("depts", departmentService.listAll());
        //查询所有的角色,显示到移动列表中
        model.addAttribute("roles", roleService.listAll());
        return "employee/input";
    }

    @RequiresPermissions(value={"保存/更新员工", "employee:saveOrUpdate"},logical=Logical.OR)
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee entity, Long[] ids) {
        //调用业务方法处理请求
        employeeService.saveOrUpdate(entity, ids);
        return "redirect:/employee/list.do";
    }

    @RequiresPermissions(value={"删除员工", "employee:delete"},logical=Logical.OR)
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        //调用业务方法处理请求
        employeeService.delete(id);
        return new JSONResult();
    }

    @RequiresPermissions(value={"批量删除员工", "employee:batchDelete"},logical=Logical.OR)
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Object batchDelete(Long[] ids) {
        employeeService.batchDelete(ids);
        return new JSONResult();
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public boolean checkName(String name) {
        //调用业务方法处理请求
        return employeeService.checkName(name);
    }

    /**
     * 导出Excel
     */
//    @RequiresRoles(value = {"HR_MGR"})
    @RequestMapping("/exportExcel")
    public ModelAndView exportExcel(HttpServletResponse resp) throws Exception {
        //从数据库中查询所有的员工数据
        List<Employee> list = employeeService.listAll();
        //========================
        //1:创建基于2003版本的工作簿
        Workbook wb = new HSSFWorkbook();
        //2:在工作簿中创建纸张
        Sheet sheet = wb.createSheet();
        //3:在纸张创建行
        Row row = sheet.createRow(0);
        //4:在行中创建细胞,设置数据
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        //=================================
        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(e.getName());
            row.createCell(1).setCellValue(e.getEmail());
            row.createCell(2).setCellValue(e.getAge());
        }
        //6:设置下载的响应头
        resp.setHeader("Content-Disposition","attachment;filename=employee.xls");
        wb.write(resp.getOutputStream());
        return null;
    }

    @RequestMapping("/imporExcel")
    public String imporExcel(MultipartFile xls) throws Exception {
        //1:创建基于2003版本的工作簿,从流中取出该Excel文件的数据
        Workbook wb = new HSSFWorkbook(xls.getInputStream());
        //2:获取纸张对象
        Sheet sheet = wb.getSheetAt(0);
        //3:从纸张对象中获取行
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //4:从行中获取细胞,从细胞中获取数据
            String name = row.getCell(0).getStringCellValue();
            String email = row.getCell(1).getStringCellValue();
            Double age = row.getCell(2).getNumericCellValue();
            //5:每一行数据封装成一个Employee对象保存到数据
            Department d = new Department();
            d.setId(1L);
            Employee e = new Employee(name, "1", age.intValue(), email, false, d, null);
            employeeService.saveOrUpdate(e, null);
        }
        return "redirect:/employee/list.do";
    }
}
