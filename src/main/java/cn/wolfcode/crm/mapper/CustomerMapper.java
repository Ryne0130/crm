package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerMapper {
    int insert(Customer record);

    List<Customer> selectForList(QueryObject qo);

    int updateByPrimaryKey(Customer record);

    void updateStatus(Customer entity);
}