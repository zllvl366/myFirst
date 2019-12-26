package com.mall.manager.service;

import com.mall.common.pojo.DatagridResult;
import com.mall.manager.pojo.TbItem;

/**
 *@author zl
 * Created by zl on 2019/12/20.
 * 订单处理相关service
 */
public interface ItemService {

    /**
     * 根据订单编号查询订单
     * @param itemId
     * @return
     */
    TbItem queryItemById(Long itemId);

    /**
     * 查询订单列表
     * @param currentPage 当前页
     * @param pageSize  每页多少条
     * @return
     */
    DatagridResult queryItemList(Integer currentPage,Integer pageSize);

}
