package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

//员工对象的业务层接口
public interface IEmployeeService {

    void saveOrUpdate(Employee entity, Long[] ids);
    void delete(Long id);

    Employee get(Long id);
    List<Employee> listAll();

    //高级查询方法
    PageInfo<Employee> query(QueryObject qo);

    boolean checkName(String name);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(Long[] ids);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    Employee getByName(String username);

    /**
     * 查询当前员工拥有的角色
     * @return
     */
    List<String> listRoles(Long employeeId);

    /**
     * 查询当前员工拥有的权限表达式
     * @return
     */
    List<String> listPermissions(Long employeeId);

    String registerEmployee(Employee employee);

}
