package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryService {
    void saveOrUpdate(SystemDictionary entity);
    void delete(Long id);

    List<SystemDictionary> listAll();
    PageInfo<SystemDictionary> query(QueryObject qo);
    //========================================
    void saveOrUpdateItem(SystemDictionaryItem entity);
    void deleteItem(Long id);

    PageInfo<SystemDictionaryItem> queryItem(QueryObject qo);

    /**
     * 根据字典目录的SN查询明细
     * @param sn
     * @return
     */
    List<SystemDictionaryItem> listItemsBySn(String sn);
}
