package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//员工对象的业务层实现
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public List<String> listRoles(Long employeeId) {
        return roleMapper.selectStringRolesByEmployeeId(employeeId);
    }

    public List<String> listPermissions(Long employeeId) {
        return permissionMapper.selectByEmployeeId(employeeId);
    }

    @Override
    public String registerEmployee(Employee employee) {
        String validationResult = validateEmployee(employee);
        if (!validationResult.equals("注册成功")) {
            return validationResult;
        }
        employee.setDefaultValues();

        try {
            employeeMapper.insertEmployee(employee);
            employeeMapper.insertEmployeeSkills(employee);
            saveEmployeeWithQrCodeImage(employee);
            return "注册成功";
        } catch (Exception e) {
            return "注册失败，发生异常：" + e.getMessage();
        }
    }


    public void saveEmployeeWithQrCodeImage(Employee employee) {
        try (InputStream inputStream = employee.getQrCodeImage().getInputStream()) {
            byte[] qrCodeImageData = IOUtils.toByteArray(inputStream);
            if (qrCodeImageData != null && qrCodeImageData.length > 0) {  // 单独判断转换后的字节数组是否为空
                Map<String, Object> params = new HashMap<>();
                params.put("id", employee.getId());
                params.put("qrCodeImage", qrCodeImageData);
                employeeMapper.insertQrCodeImage(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getByName(String username) {
        return employeeMapper.selectByName(username);
    }

    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
    }

    public boolean checkName(String name) {
        return employeeMapper.selectByName(name) == null;
    }

    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> list = employeeMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public void saveOrUpdate(Employee entity, Long[] ids) {
        //先正常处理员工信息
        if (entity.getId() == null) {
            //先对密码加密再保存到数据库
            Md5Hash hash = new Md5Hash(entity.getPassword(), entity.getName());
            //重新设置加密后的密码
            entity.setPassword(hash.toString());
            employeeMapper.insert(entity);
        } else {
            //先删除当前对象跟角色的旧关系
            employeeMapper.deleteRelation(entity.getId());
            employeeMapper.updateByPrimaryKey(entity);
        }
        //再处理当前员工与角色的关系
        if (ids != null) {
            for (Long roleId : ids) {
                //把关系保存到中间表中
                employeeMapper.insertRelation(entity.getId(), roleId);
            }
        }
    }

    public void delete(Long id) {
        if (id != null) {
            //先删除关系,再删除员工对象
            employeeMapper.deleteRelation(id);
            employeeMapper.deleteByPrimaryKey(id);
        }
    }

    public Employee get(Long id) {

        return employeeMapper.selectByPrimaryKey(id);
    }

    public List<Employee> listAll() {

        return employeeMapper.selectAll();
    }


    public String validateEmployee(Employee employee) {
        // 校验姓名
        if (employee.getName() == null || employee.getName().isEmpty()) {
            return "姓名不能为空";
        }

        // 校验微信号
        if (employee.getWechatNumber() == null || employee.getWechatNumber().isEmpty()) {
            return "微信号不能为空";
        }

        // 校验微信名
        if (employee.getWechatName() == null || employee.getWechatName().isEmpty()) {
            return "微信名不能为空";
        }

        // 校验手机号
        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty() || !isValidPhoneNumber(employee.getPhoneNumber())) {
            return "手机号不能为空或格式不正确，手机号必须为 11 位数字";
        }

        // 校验企业微信名
        if (employee.getEnterpriseWechatName() == null || employee.getEnterpriseWechatName().isEmpty()) {
            return "企业微信名不能为空";
        }

        // 校验二维码图片
        if (employee.getQrCodeImage() == null || employee.getQrCodeImage().isEmpty()) {
            return "微信收款二维码不能为空";
        }


        return "注册成功";
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // 假设有效的手机号是 11 位数字
        return phoneNumber.matches("\\d{11}");
    }
}
