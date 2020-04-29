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

import java.util.*;

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
        result.setRows(pageInfo.getList());
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

    @Override
    public E3Result optItem(String opt, String ids) {
        //对传入id进行处理
        String[] split = ids.split(",");
        List<Long> idsL = null;
        if(null!=split && split.length>0){
            idsL = new ArrayList<>();
            for (String id: split) {
                idsL.add(Long.parseLong(id));
            }
        }
        if(null==idsL || idsL.size() < 1){
            return E3Result.build(404,"请选择正确的商品Id");
        }
        //普通条件封装
        Map param = null;
        if("delete".equals(opt)){//删除商品
            param = new HashMap();
            param.put("ids",idsL);
            param.put("status","3");
            tbItemMapper.updateItemList(param);
        }else if("instock".equals(opt)){//商品下架
            param = new HashMap();
            param.put("ids",idsL);
            param.put("status","2");
            tbItemMapper.updateItemList(param);
        }else if("reshelf".equals(opt)){//商品上架
            param = new HashMap();
            param.put("ids",idsL);
            param.put("status","1");
            tbItemMapper.updateItemList(param);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result optItemInfoOpt(String infoType, String type2, String opt, String id) {
        //@TODO 缺少数据，暂时不做
        return E3Result.ok();
    }
}
