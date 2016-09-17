package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;


@Alias("messageInfo")
@Data
public class MessageInfo implements Serializable{

    private int id;
    private int relatedId;
    private int userId;
    private String username;
    private String userImage;
    private int postId;
    private int status;
    private Date updateTime;
}
