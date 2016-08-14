package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;


@Alias("postItem")
@Data
public class PostItem implements Serializable{

    private int id; //Id主键,
    private String title;   // 标题
    private String company; // 公司
    private String website; // 公司网站
    private String address; // 详细地址
    private String reward;  // 薪水与报酬
    private String description; // 职责描述

    private int userId;
    private String username;
    private String userimage;

    private Date updatetime;  // 更新时间
}
