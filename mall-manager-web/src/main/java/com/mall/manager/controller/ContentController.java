package com.mall.manager.controller;

import com.mall.cms.service.ContentService;
import com.mall.common.pojo.DatagridResult;
import com.mall.common.pojo.E3Result;
import com.mall.common.utils.JsonUtils;
import com.mall.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zll on 2020/1/14.
 * 首页广告内容管理
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据广告分类Id查询广告内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public DatagridResult queryTbContentList(@RequestParam(defaultValue = "1") Integer pageNum, Integer pageSize, Long categoryId){
        if(null == categoryId){
            return null;
        }
        return contentService.queryTbContentList(pageNum,pageSize,categoryId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public E3Result saveContent(TbContent tbContent){
        return contentService.saveTbContent(tbContent);
    }
}
