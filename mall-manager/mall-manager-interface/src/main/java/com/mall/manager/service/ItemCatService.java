package com.mall.manager.service;

import com.mall.common.pojo.TreeNodeResult;

import java.util.List;

/**
 * Created by zll on 2019/12/24.
 * 商品分类
 */
public interface ItemCatService {
    /**
     * 根据商品分类父节点查询子分类节点
     * @param parentId
     * @return
     */
    List<TreeNodeResult> queryItemCatList(Long parentId);
}
