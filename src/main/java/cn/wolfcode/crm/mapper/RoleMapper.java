package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//角色的mapper接口
public interface RoleMapper {
    void insert(Role entity);
    void updateByPrimaryKey(Role entity);
    void deleteByPrimaryKey(Long id);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId,
                        @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<String> selectStringRolesByEmployeeId(Long employeeId);
}
