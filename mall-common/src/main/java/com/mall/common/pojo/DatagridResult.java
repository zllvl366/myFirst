package com.mall.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zll on 2019/12/24.
 */
@Data
public class DatagridResult implements Serializable {
    /**
     * 总数
     */
    private long total;
    /**
     * 数据
     */
    private List rows;
}
