package com.mall.manager.service;

import com.mall.common.pojo.TreeNodeResult;
import com.mall.manager.mapper.TbItemCatMapper;
import com.mall.manager.pojo.TbItemCat;
import com.mall.manager.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zll on 2019/12/24.
 * 商品类别Service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeNodeResult> queryItemCatList(Long parentId) {

        if (null != parentId){
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
            List<TreeNodeResult> resultList = null;
            if (list.size()>0){
                resultList = new ArrayList<>();
                TreeNodeResult result = null;
                for (TbItemCat cat: list) {
                    result = new TreeNodeResult();
                    result.setId(cat.getId());
                    result.setText(cat.getName());
                    //默认的如果是父节点就关闭，子节点就打开
                    result.setState(cat.getIsParent()?"closed":"open");
                    resultList.add(result);
                }
            }
            return resultList;
        }
        return null;
    }
}
