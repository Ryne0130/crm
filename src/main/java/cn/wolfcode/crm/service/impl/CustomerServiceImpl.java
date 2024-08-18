package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public void updateStatus(Customer entity) {
        customerMapper.updateStatus(entity);
    }

    public PageInfo<Customer> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> list = customerMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public void saveOrUpdate(Customer entity) {
        if (entity.getId() == null) {
            //自动填入录入人/录入时间/营销人员
            entity.setInputTime(new Date());
            entity.setInputUser(UserContext.getCurrentUser());
            entity.setSeller(UserContext.getCurrentUser());
            //手动设置客户状态
            entity.setStatus(Customer.STATUS_NORMAL);
            customerMapper.insert(entity);
        } else {
            customerMapper.updateByPrimaryKey(entity);
        }
    }
}
