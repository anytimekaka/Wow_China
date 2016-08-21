package com.wowchina.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class PostListRequest implements Serializable {

    private int industryId;      // 行业分类id
    private int cityId; //城市Id
    private int majorId;
    private String keyword;
    private int currentPage;   // 当前页
    private int pageSize; //每页数量
}
