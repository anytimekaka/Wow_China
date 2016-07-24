package com.wowchina.model;

import lombok.Data;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class EditUserInfoRequest {

    private int userId;
    private String token;

    private String realname;   //用户真实姓名
    private String university;   //学校
    private int majorid;   //所学专业id（仅此一个）
    private String industryids;   //工作行业（多选，以『,』分割）
    private String skills;   //特长技能描述
    private String location;   //当前所在地
    private String email;   //email
    private String tel;   //手机号码
    private String certificates;   //证书
    private String linkedinid;   //LinkedIn id
    private String linkedinusername;   //LinkedIn username
    private String linkedinprofileurl;   //LinkedIn profile url
    private String facebook;   //FaceBook账号
}
