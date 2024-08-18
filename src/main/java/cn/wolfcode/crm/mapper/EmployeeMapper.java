package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    /**
     * 通过信息查询员工对象
     * @param username
     * @param password
     * @return
     */
    Employee selectByInfo(@Param("username") String username,
                          @Param("password") String password);

    List<Employee> selectForList(QueryObject qo);

    int deleteByPrimaryKey(Long id);

    int insert(Employee entity);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity);

    void insertRelation(@Param("employeeId") Long employeeId,
                        @Param("roleId") Long roleId);

    void deleteRelation(Long employeeId);

    Employee selectByName(String name);

    void batchDelete(Long[] ids);


    void insertQrCodeImage(Map<String, Object> map);

    void insertEmployee(Employee employee);

    void insertEmployeeSkills(Employee employee);


    // 根据姓名和密码查询员工信息
    Employee selectEmployeeByNameAndPassword(Map<String, Object> params);
}