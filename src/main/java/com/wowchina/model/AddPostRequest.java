package com.wowchina.model;

import lombok.Data;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class AddPostRequest {

    private int userId;
    private String token;

    private int id;         // id
    private String title;   // 标题
    private String company; // 公司
    private String website; // 公司网站
    private int industryid; // 所属行业
    private String opento;  // 面向专业
    private int headcount;  // 预计人数
    private int eligible;   // 已确定人数，不能大于预计需要人数
    private int cityid;     // 所在城市
    private String address; // 详细地址
    private String reward;  // 薪水与报酬
    private String description; // 职责描述
    private String deadline;    // 截止时间
}
