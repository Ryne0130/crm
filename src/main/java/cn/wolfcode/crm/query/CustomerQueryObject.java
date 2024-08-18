package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class CustomerQueryObject extends QueryObject {
    private Integer status = 0; //客户状态,默认查询潜在客户
    private Long sellerId = -1L;
}
