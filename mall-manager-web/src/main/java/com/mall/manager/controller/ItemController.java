package com.mall.manager.controller;

import com.mall.common.pojo.DatagridResult;
import com.mall.manager.pojo.TbItem;
import com.mall.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;

/**
 * @author zl
 * Created by zl on 2019/12/20.
 * 订单处理的Controller
 */
@RequestMapping("/item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据订单id查询订单
     * @param itemId
     * @return
     */
    @RequestMapping("/test/{itemId}")
    @ResponseBody
    public TbItem queryItemById(@PathVariable Long itemId){
        if (null !=itemId){
            return itemService.queryItemById(itemId);
        }
        return null;
    }

    /**
     * 查询订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public DatagridResult queryItemList(@RequestParam(defaultValue = "1") Integer pageNum,Integer pageSize){
        return itemService.queryItemList(pageNum,pageSize);
    }
}
