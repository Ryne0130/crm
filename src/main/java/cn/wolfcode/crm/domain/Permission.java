package cn.wolfcode.crm.domain;

import lombok.*;

//权限对象
@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseDomain {
    private String name; //权限名称

    private String expression; //权限表达式.权限的唯一标志
}