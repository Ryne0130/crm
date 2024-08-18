package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    /**
     * 根据目录ID查询明细
     * @return
     */
    List<SystemDictionaryItem> selectForList(QueryObject qo);

    int updateByPrimaryKey(SystemDictionaryItem record);

    Integer selectMaxSequenceByParentId(Long parentId);

    void deleteByParentId(Long parentId);

    List<SystemDictionaryItem> selectBySn(String sn);
}