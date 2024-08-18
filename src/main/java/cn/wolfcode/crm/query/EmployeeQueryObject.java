package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class EmployeeQueryObject extends QueryObject {
    private Long deptId = -1L;
}
