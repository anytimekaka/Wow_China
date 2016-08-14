package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Alias("postListParam")
@Data
public class PostListParam implements Serializable {

    private int industryId;      // 行业分类id
    private int low;   // 分页下限编号
    private int high; // 分页上限编号
}
