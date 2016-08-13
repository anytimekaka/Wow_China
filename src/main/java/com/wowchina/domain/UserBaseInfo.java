package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("userBaseInfo")
@Data
public class UserBaseInfo implements Serializable{

    private int id;  //用户主键id
    private String username;   //用户名 
    private String userimage;   //用户头像图片名称，默认为系统自带图片，上传后用用户名重命名，沿用之前后缀
}
