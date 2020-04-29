package com.mall.manager.service;

import com.mall.common.pojo.DatagridResult;
import com.mall.common.pojo.E3Result;
import com.mall.manager.pojo.TbItem;

/**
 *@author zl
 * Created by zl on 2019/12/20.
 * 商品处理相关service
 */
public interface ItemService {

    /**
     * 根据商品编号查询订单
     * @param itemId
     * @return
     */
    TbItem queryItemById(Long itemId);

    /**
     * 查询商品列表
     * @param currentPage 当前页
     * @param pageSize  每页多少条
     * @return
     */
    DatagridResult queryItemList(Integer currentPage,Integer pageSize);

    /**
     * 保存商品
     * @param tbItem
     * @return
     */
    E3Result save(TbItem tbItem);

    /**
     * 商品操作
     * @param opt 操作类型 delete- 删除
     * @param ids 商品id集合
     * @return
     */
    E3Result optItem(String opt, String ids);

    /**
     * 详情相关操作
     * @param infoType 信息类型 query-商品主体 param-商品参数
     * @param type2 参数类型 item-商品参数
     * @param opt 操作类型 desc-详情 query-参数
     * @return
     */
    E3Result optItemInfoOpt(String infoType, String type2, String opt, String id);
}
