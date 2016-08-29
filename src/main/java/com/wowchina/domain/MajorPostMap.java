package com.wowchina.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("majorPostMap")
@Data
public class MajorPostMap implements Serializable{

    private int id;
    private int majorId;
    private int postId;
}
