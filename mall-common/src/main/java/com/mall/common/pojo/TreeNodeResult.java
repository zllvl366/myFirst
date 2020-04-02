package com.mall.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zll on 2019/12/24.
 * 树形数据
 */
@Data
public class TreeNodeResult implements Serializable{
    /**
     * 节点Id
     */
    private Long id;
    /**
     * 节点名称
     */
    private String text;
    /**
     * 节点状态 open为打开 closed 为关闭 默认open
     */
    private String state;
    /**
     * 父级Id
     */
    private Long parentId;
}
