package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;


@Alias("post")
@Data
public class Post implements Serializable{

    private int id; //Id主键,
    private String title;   // 标题
    private String company; // 公司
    private String website; // 公司网站
    private int industryid; // 所属行业
    private String opento;  // 面向专业
    private int headcount;  // 预计人数
    private int eligible;   // 已确定人数，不能大于预计需要人数
    private int status;     // 状态：0-进行中，1-已关闭
    private int cityid;     // 所在城市
    private String address; // 详细地址
    private String reward;  // 薪水与报酬
    private String description; // 职责描述
    private String deadline;      // 截止日期
    private int userid;         // 发布者ID
    private Date updatetime;  // 更新时间
}
