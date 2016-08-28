package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;


@Alias("apply")
@Data
public class Apply implements Serializable{

    private int id;
    private int userId;
    private int postId;
}
