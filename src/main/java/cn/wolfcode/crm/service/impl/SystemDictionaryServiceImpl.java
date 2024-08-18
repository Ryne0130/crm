package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.mapper.SystemDictionaryMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper dictionaryMapper;
    @Autowired
    private SystemDictionaryItemMapper itemMapper;

    public List<SystemDictionaryItem> listItemsBySn(String sn) {
        return itemMapper.selectBySn(sn);
    }

    public void saveOrUpdateItem(SystemDictionaryItem entity) {
        //设置序列自动增长问题
        if (entity.getSequence() == null) {
            //1:查询当前目录中明细的最大序列
            Integer sequence = itemMapper.selectMaxSequenceByParentId(entity.getParentId());
            //2:处理sequence为null的问题
            sequence = sequence == null ? 1 : sequence + 1;
            //3:设置到对象的序列中
            entity.setSequence(sequence);
        }

        if (entity.getId() == null) {
            itemMapper.insert(entity);
        } else {
            itemMapper.updateByPrimaryKey(entity);
        }
    }

    public void deleteItem(Long id) {
        itemMapper.deleteByPrimaryKey(id);
    }

    public PageInfo<SystemDictionaryItem> queryItem(QueryObject qo) {
        //PageHelper.startPage方法参数3用于结果集的排序
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<SystemDictionaryItem> list = itemMapper.selectForList(qo);
        return new PageInfo<>(list);
    }
    //=========================================================
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        //调用插件提供的分页方法
        //该方法仅对下一个执行的查询语句有效
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<SystemDictionary> list = dictionaryMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    public void saveOrUpdate(SystemDictionary entity) {
        if (entity.getId() == null) {
            dictionaryMapper.insert(entity);
        } else {
            dictionaryMapper.updateByPrimaryKey(entity);
        }
    }

    public void delete(Long id) {
        if (id != null) {
            //先删除字典明细,再删除字典
            itemMapper.deleteByParentId(id);
            dictionaryMapper.deleteByPrimaryKey(id);
        }
    }

    public List<SystemDictionary> listAll() {
        return dictionaryMapper.selectAll();
    }
}
