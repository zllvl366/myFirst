package com.mall.common.pojo;

import lombok.Data;

/**
 * Created by zll+ on 2020/4/8.
 * 首页轮播广告封装对象
 */
@Data
public class AdResult {
    /**
     * 图片标题
     */
    private String title;
    /**
     * 图片链接
     */
    private String src;
    /**
     * 宽屏图片链接
     */
    private String srcB;
    /**
     * 图片宽度
     */
    private Integer width;
    /**
     * 宽屏图片宽度
     */
    private Integer widthB;
    /**
     * 图片高度
     */
    private Integer height;
    /**
     * 宽屏图片高度
     */
    private Integer heightB;
    /**
     * 点击图片跳转的路径
     */
    private String href;



}
