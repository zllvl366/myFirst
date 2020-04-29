package com.mall.cms.service;

import cn.hutool.core.date.DateUtil;
import com.mall.common.pojo.E3Result;
import com.mall.common.pojo.TreeNodeResult;
import com.mall.manager.mapper.TbContentCategoryMapper;
import com.mall.manager.pojo.TbContentCategory;
import com.mall.manager.pojo.TbContentCategoryExample;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zll on 2020/1/13.
 * 前台内容分类管理Service实现
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Override
    public List<TreeNodeResult> queryContentCategoryList(Long parentId) {
        //根据父级Id查询分类内容列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> categoryList = categoryMapper.selectByExample(example);

        List<TreeNodeResult> resultList = new ArrayList<>();
        TreeNodeResult result = null;
        for (TbContentCategory category : categoryList){
            result = new TreeNodeResult();
            result.setId(category.getId());
            result.setText(category.getName());
            //如果是父节点就显示关闭，叶子节点就打开
            result.setState(category.getIsParent()?"closed":"oppen");
            result.setParentId(category.getParentId());
            resultList.add(result);
        }
        return resultList;
    }

    @Override
    public E3Result saveContent(Long parentId, String name) {
        //补全内容分类属性
        TbContentCategory category = new TbContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        //状态默认为1 可用
        category.setStatus(1);
        //默认排序规则为1
        category.setSortOrder(1);
        //新建的默认不是父节点
        category.setIsParent(false);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        //保存
        categoryMapper.insert(category);

        //新增子节点后，父级的isParent变更为true
        TbContentCategory parentCategory = new TbContentCategory();
        parentCategory.setId(parentId);
        parentCategory.setIsParent(true);
        categoryMapper.updateByPrimaryKeySelective(parentCategory);

        return E3Result.ok(category);
    }

    @Override
    public E3Result updateContent(Long id, String name) {

        TbContentCategory category = new TbContentCategory();
        category.setId(id);
        category.setName(name);
        category.setUpdated(new Date());
        categoryMapper.updateByPrimaryKeySelective(category);

        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(Long parentId, Long id) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> categoryList = categoryMapper.selectByExample(example);
        if (categoryList.size()>0){//有子分类
            return E3Result.build(1,"当前分类下有子分类，不能直接删除");
        }

        example  = new TbContentCategoryExample();
        criteria = example.createCriteria();
        if (null!=parentId){
            criteria.andParentIdEqualTo(parentId);
        }
        criteria.andIdEqualTo(id);
        categoryMapper.deleteByExample(example);

        return E3Result.ok();
    }
}
