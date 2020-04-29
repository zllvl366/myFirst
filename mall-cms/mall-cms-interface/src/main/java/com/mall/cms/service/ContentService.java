package com.mall.cms.service;

import com.mall.common.pojo.DatagridResult;
import com.mall.common.pojo.E3Result;
import com.mall.manager.pojo.TbContent;

import java.util.List;

/**
 * Created by zll on 2020/1/14.
 * 首页广告的Service
 */
public interface ContentService {

    /**
     * 根据分类查询广告内容集合
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    DatagridResult queryTbContentList(Integer pageNum, Integer pageSize,Long categoryId);

    /**
     * 保存广告内容
     * @param tbContent
     * @return
     */
    E3Result saveTbContent(TbContent tbContent);

    /**
     * 根据分类获取当前分类下的所有广告
     * @param categoryId
     * @return
     */
    List<TbContent> queryContentList(Long categoryId);

}
