package com.mall.cms.service;

import com.mall.common.pojo.E3Result;
import com.mall.common.pojo.TreeNodeResult;

import java.util.List;

/**
 * Created by zll on 2020/1/13.
 * 首页内容分类管理Service
 */
public interface ContentCategoryService {
    /**
     * 根据父级Id获取下级商品内容分类集合
     * @param parentId
     * @return
     */
    List<TreeNodeResult> queryContentCategoryList(Long parentId);
    /**
     * 创建广告分类
     * @param parent
     * @param name
     * @return
     */
    E3Result saveContent(Long parent,String name);

    /**
     * 修改广告分类
     * @param id
     * @param name
     * @return
     */
    E3Result updateContent(Long id,String name);

    /**
     * 删除广告分类
     * @param parentId
     * @param id
     * @return
     */
    E3Result deleteContent(Long parentId,Long id);
}
