package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void saveOrUpdate(Department entity);
    void delete(Long id);

    List<Department> listAll();

    /**
     * 分页查询
     * @param qo
     * @return 分页信息
     */
    PageInfo<Department> query(QueryObject qo);
}
