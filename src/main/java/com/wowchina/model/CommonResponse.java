package com.wowchina.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable{

    /**
     * 0：成功，1：失败
     */
    private int code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T result;

    public CommonResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public CommonResponse(){
        this.code=0;
        this.message="success";
    }
}
