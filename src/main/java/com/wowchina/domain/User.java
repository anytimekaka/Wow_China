package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("user")
@Data
public class User implements Serializable{

    private int id;  //用户主键id
    private String username;   //用户名 
    private String password;   //密码 
    private String token;   //用户验证token 
    private String userimage;   //用户头像图片名称，默认为系统自带图片，上传后用用户名重命名，沿用之前后缀 
    private String realname;   //用户真实姓名 
    private String university;   //学校 
    private String majorid;   //所学专业id（仅此一个） 
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
