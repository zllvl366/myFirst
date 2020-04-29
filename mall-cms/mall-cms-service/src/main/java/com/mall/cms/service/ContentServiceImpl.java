package com.mall.cms.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.DatagridResult;
import com.mall.common.pojo.E3Result;
import com.mall.common.utils.JsonUtils;
import com.mall.manager.mapper.TbContentMapper;
import com.mall.manager.pojo.TbContent;
import com.mall.manager.pojo.TbContentExample;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.List;

/**
 * Created by zll on 2020/1/14.
 * 广告内容管理Service实现
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    @Override
    public DatagridResult queryTbContentList(Integer pageNum, Integer pageSize,Long categoryId) {
        if (null == pageNum){
            pageNum = 1;
        }
        if (null == pageSize){
            pageSize = 20;
        }
        // PageHelper开启分页
        PageHelper.startPage(pageNum,pageSize);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);
        // 获取分页数据对象
        PageInfo pageInfo = new PageInfo(list);
        // 封装结果集
        DatagridResult result = new DatagridResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public E3Result saveTbContent(TbContent tbContent) {
        // 创建和更新时间
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        // 保存
        contentMapper.insert(tbContent);
        // 刷新缓存中的数据
        try{
           /* // 根据分类Id查询广告内容列表
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(tbContent.getCategoryId());
            List<TbContent> list = contentMapper.selectByExample(example);
            // 将查询结果存入redis
            jedisCluster.hset(REDIS_CONTENT_KEY,tbContent.getCategoryId().toString(),JsonUtils.objectToJson(list));*/
           //刷新缓存
            jedisCluster.hdel(REDIS_CONTENT_KEY,tbContent.getCategoryId().toString());
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return E3Result.ok(tbContent);
    }

    @Override
    public List<TbContent> queryContentList(Long categoryId) {
        // 先从redis取
        try{
            // 从redis获取内容列表信息
            String json = jedisCluster.hget(REDIS_CONTENT_KEY, categoryId.toString());
            // 如果不为空将json转成集合对象，返回
            if(StrUtil.isNotEmpty(json)){
                return JsonUtils.jsonToList(json,TbContent.class);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        //根据分类Id查询广告内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);
        try{
            //将查询结果存入redis
            jedisCluster.hset(REDIS_CONTENT_KEY,categoryId.toString(),JsonUtils.objectToJson(list));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

}
