package com.mall.manager.service;

import com.mall.manager.mapper.TbItemMapper;
import com.mall.manager.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zl
 * Created by zl on 2019/12/20.
 * 订单处理业务实现
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
}
