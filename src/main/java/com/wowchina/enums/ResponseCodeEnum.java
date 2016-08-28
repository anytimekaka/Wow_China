package com.wowchina.enums;

import lombok.Getter;

/**
 * 返回代码与消息内容
 * Created by wangguisheng on 16/8/26.
 */
public enum ResponseCodeEnum {
    SUCCESS(0,"成功"),
    ALREADY_APPLY(10, "已申请，不能重复申请"),
    USER_IS_POSTER(11, "是发起者，不能申请");

    @Getter
    private int code;

    @Getter
    private String value;

    private ResponseCodeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
