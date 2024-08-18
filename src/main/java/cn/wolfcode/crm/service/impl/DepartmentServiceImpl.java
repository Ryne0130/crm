package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public PageInfo<Department> query(QueryObject qo) {
        //调用插件提供的分页方法
        //该方法仅对下一个执行的查询语句有效
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Department> list = departmentMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public void saveOrUpdate(Department entity) {
        if (entity.getId() == null) {
            departmentMapper.insert(entity);
        } else {
            departmentMapper.updateByPrimaryKey(entity);
        }
    }

    public void delete(Long id) {
        if (id != null) {
            departmentMapper.deleteByPrimaryKey(id);
        }
    }

    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }
}
