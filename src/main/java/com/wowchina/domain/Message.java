package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;


@Alias("message")
@Data
public class Message implements Serializable{

    private int id;
    private int userId;
    private int postId;
    private int type;
    private int read;
    private Date addTime;
    private String remark;
}
