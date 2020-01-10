package com.mall.manager.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.DatagridResult;
import com.mall.common.pojo.E3Result;
import com.mall.common.utils.IDUtils;
import com.mall.manager.mapper.TbItemMapper;
import com.mall.manager.pojo.TbItem;
import com.mall.manager.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zl
 * Created by zl on 2019/12/20.
 * 商品处理业务实现
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem queryItemById(Long itemId) {
        if(null != itemId){
           return tbItemMapper.selectByPrimaryKey(itemId);
        }
        return null;
    }

    @Override
    public DatagridResult queryItemList(Integer currentPage, Integer pageSize) {

        if(null == currentPage){
            currentPage = 1;
        }

        if (null == pageSize){
            pageSize = 30;
        }
        //开启分页
        PageHelper.startPage(currentPage,pageSize);
        //查询
        TbItemExample example = new TbItemExample();
        List<TbItem> itemList = tbItemMapper.selectByExample(example);
        //获取分页数据对象
        PageInfo<TbItem> pageInfo = new PageInfo(itemList);
        //封装结果集
        DatagridResult result = new DatagridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(itemList);
        return result;
    }

    @Override
    public E3Result save(TbItem tbItem) {
        //设置商品Id
        tbItem.setId(IDUtils.genItemId());
        //设置上下架状态1-正常 2-下架 3-删除
        tbItem.setStatus((byte)1);
        //创建日期
        tbItem.setCreated(DateUtil.date());
        tbItem.setUpdated(DateUtil.date());
        //保存商品信息
        tbItemMapper.insert(tbItem);
        return E3Result.ok();
    }
}
