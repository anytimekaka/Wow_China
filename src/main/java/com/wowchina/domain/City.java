package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("city")
@Data
public class City implements Serializable{

    private int id;  //id
    private String cityname;   //cityname
}
