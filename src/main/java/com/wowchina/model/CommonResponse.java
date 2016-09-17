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

    private CommonResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    private CommonResponse(){
        this.code=0;
        this.message="success";
    }

    public static CommonResponse<String> getInstance(int code, String message){
        CommonResponse<String> response = new CommonResponse<String>(code, message);
        response.setResult("");
        return response;
    }

    public static CommonResponse successResponse(String message){
        return CommonResponse.getInstance(0, message);
    }

    public static CommonResponse successResponse(){
        return CommonResponse.getInstance(0, "success");
    }

    public  static CommonResponse errorResponse(String message){
        return CommonResponse.getInstance(1, message);
    }

    public  static CommonResponse errorResponse(int code, String message){
        return CommonResponse.getInstance(code, message);
    }

    public static CommonResponse authErrorResponse(){
        return CommonResponse.errorResponse("auth error");
    }
}
