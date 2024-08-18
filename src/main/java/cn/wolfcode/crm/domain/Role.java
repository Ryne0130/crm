package cn.wolfcode.crm.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

//角色对象
@Setter@Getter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseDomain {
    private String name; //角色名称
    private String sn; //角色编码
    //关联many方对象
    private List<Permission> permissions = new ArrayList<>();
}
