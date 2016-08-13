package com.wowchina.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserRegisterInfo implements Serializable{

    private int userId;  //用户主键id
    private String token;   //用户验证token
}
