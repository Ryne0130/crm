package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int insert(Permission record);

    List<Permission> selectAll();

    List<Permission> selectForList(QueryObject qo);

    List<String> selectExpressions();

    List<String> selectByEmployeeId(Long employeeId);
}