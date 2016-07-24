package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("major")
@Data
public class Major implements Serializable{

    private int id;  //id
    private String major;   //专业名
}
