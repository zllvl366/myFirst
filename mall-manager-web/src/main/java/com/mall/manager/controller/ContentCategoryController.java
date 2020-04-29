package com.mall.manager.controller;

import com.mall.cms.service.ContentCategoryService;
import com.mall.common.pojo.E3Result;
import com.mall.common.pojo.TreeNodeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zll on 2020/1/13.
 * 内容分类管理的Controller 
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService categoryService;

    /**
     *广告管理-内容分类管理树
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNodeResult> list(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return categoryService.queryContentCategoryList(parentId);
    }

    /**
     * 创建广告分类
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public E3Result create(Long parentId,String name){
        return categoryService.saveContent(parentId, name);
    }

    /**
     * 修改广告分类
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public E3Result update(Long id,String name){
        return categoryService.updateContent(id, name);
    }

    /**
     * 删除广告分类
     * @param parentId
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public E3Result delete(Long parentId,Long id){
        if(null==id){
            return E3Result.build(1,"id不能为空");
        }
        return categoryService.deleteContent(parentId, id);
    }
}
