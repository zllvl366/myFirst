package com.mall.manager.controller;

import com.mall.common.pojo.E3Result;
import com.mall.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zll on 2019/12/24.
 * 系统相关业务
 */
@Controller
public class SystemController {
    /**
     * 商品处理相关的service
     */
    @Autowired
    private ItemService itemService;

    @RequestMapping("/")//首页
    public String index(){
        return "index";
    }

    @RequestMapping("/system/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    /**
     *状态相关操作
     * @param type 类型 item-商品
     * @param opt 操作类型
     * @param ids 商品Id
     * @return
     */
    @RequestMapping("/rest/{type}/{opt}")
    @ResponseBody
    public E3Result restItemSatusOpt(@PathVariable String type,@PathVariable String opt,String ids){
        if(StringUtils.isEmpty(ids)){
            return E3Result.build(404,"参数不能为空！");
        }
        if("item".equals(type)){//商品操作
            return itemService.optItem(opt,ids);
        }
        return  E3Result.build(404,"操作不合法");
    }

    /**
     *详情相关操作
     * @param type 类型 item-商品
     * @param infoType 信息类型 query-商品主体 param-商品参数
     * @param type2 参数类型 item-商品参数
     * @param opt 操作类型 desc-详情
     * @param id
     * @return
     */
    @RequestMapping("/rest/{type}/{infoType}/{type2}/{opt}")
    @ResponseBody
    public E3Result restItemInfo(@PathVariable String type,@PathVariable String infoType,
                                 @PathVariable String type2,@PathVariable String opt,String id){
        if(StringUtils.isEmpty(id)){
            return E3Result.build(404,"参数不能为空！");
        }
        if("item".equals(type)){//商品操作
            return itemService.optItemInfoOpt(infoType,type2,opt,id);
        }
        return  E3Result.build(404,"操作不合法");
    }
}
