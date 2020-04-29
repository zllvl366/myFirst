package com.mall.portal.controller;

import com.mall.cms.service.ContentService;
import com.mall.common.pojo.AdResult;
import com.mall.common.utils.JsonUtils;
import com.mall.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zll on 2020/1/10.
 * 首页
 */
@Controller
public class IndexController {
    /**
     * 广告内容处理的service
     */
    @Autowired
    private ContentService contentService;

    //广告位
    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    //宽度
    @Value("${AD1_WIDTH}")
    private int AD1_WIDTH;
    //高度
    @Value("${AD1_HEIGHT}")
    private int AD1_HEIGHT;
    //大屏宽度
    @Value("${AD1_WIDTHB}")
    private int AD1_WIDTHB;
    //大屏高度
    @Value("${AD1_HEIGHTB}")
    private int AD1_HEIGHTB;

    @RequestMapping("/")
    public String index(Model model){
        //1 加载配置文件中的广告位ID 确定广告位置
        //2 获取广告
        List<TbContent> tbContents = contentService.queryContentList(AD1_CATEGORY_ID);
        //3 将广告内容封装成页面对象
        List<AdResult> adResults = new ArrayList<>();
        AdResult adResult;
        for (TbContent tc:tbContents) {
            adResult = new AdResult();
            adResult.setTitle(tc.getTitle());
            adResult.setSrc(tc.getPic());
            adResult.setSrcB(tc.getPic2());
            adResult.setHref(tc.getUrl());
            // 页面属性
            adResult.setHeight(AD1_HEIGHT);
            adResult.setHeightB(AD1_HEIGHTB);
            adResult.setWidth(AD1_WIDTH);
            adResult.setWidthB(AD1_WIDTHB);
            adResults.add(adResult);
        }
        // 4转成json放入request
        String json = JsonUtils.objectToJson(adResults);
        model.addAttribute("ad1",json);
        return "index";
    }
}
