package com.mall.manager.service;

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

}
