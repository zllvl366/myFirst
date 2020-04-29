package com.mall.manager.mapper;

import com.mall.manager.pojo.TbItem;
import com.mall.manager.pojo.TbItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExampleWithBLOBs(TbItemExample example);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKeyWithBLOBs(TbItem record);

    int updateByPrimaryKey(TbItem record);

    /**
     * 根据条件更新多条商品记录
     * @param param
     */
    void updateItemList(Map param);
}