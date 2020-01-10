package com.mall.manager.controller;

import com.mall.common.pojo.TreeNodeResult;
import com.mall.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zll on 2019/12/24.
 * 商品分类的controller
 */
@Controller
@RequestMapping("itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNodeResult> queryItemCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        return itemCatService.queryItemCatList(parentId);
    }



}
