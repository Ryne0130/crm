package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemDictionaryQueryObject extends QueryObject {
    private Long parentId = -1L; //字典目录ID
}
