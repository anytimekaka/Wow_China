package com.wowchina.enums;

import lombok.Getter;

/**
 * Created by wangguisheng on 16/8/26.
 */
public enum MessageStatusEnum {
    UNREPLY(1, "未回复"),
    REPLIED(2, "已回复"),
    APPLIED(3, "已申请"),
    ACCEPTED(4, "接受"),
    REJECTED(5, "拒绝");

    @Getter
    private int code;

    @Getter
    private String value;

    private MessageStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
