package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerService {
    void saveOrUpdate(Customer entity);
    PageInfo<Customer> query(QueryObject qo);

    /**
     * 更新客户状态
     * @param entity
     */
    void updateStatus(Customer entity);
}
