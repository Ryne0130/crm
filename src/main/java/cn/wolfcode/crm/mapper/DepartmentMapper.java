package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> selectForList(QueryObject qo);
}