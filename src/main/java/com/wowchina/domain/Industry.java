package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("industry")
@Data
public class Industry implements Serializable{

    private int id;  //id
    private String industry;   //行业名
}
