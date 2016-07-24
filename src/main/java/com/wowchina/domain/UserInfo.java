package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;


@Data
public class UserInfo implements Serializable{

    private int id;  //用户主键id
    private String userimage;   //用户头像图片名称，默认为系统自带图片，上传后用用户名重命名，沿用之前后缀
    private String realname;   //用户真实姓名 
    private String university;   //学校 
    private Major major;   //所学专业（仅此一个）
    private List<Industry> industrys;   //工作行业
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
