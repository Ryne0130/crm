package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> list = roleMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public void saveOrUpdate(Role entity, Long[] ids) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        } else {
            roleMapper.deleteRelation(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        }
        if (ids != null) {
            for (Long permissionId : ids) {
                roleMapper.insertRelation(entity.getId(), permissionId);
            }
        }
    }

    public void delete(Long id) {
        if (id != null) {
            roleMapper.deleteRelation(id);
            roleMapper.deleteByPrimaryKey(id);
        }
    }

    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> listAll() {
        return roleMapper.selectAll();
    }
}
