package com.wowchina.model;

import lombok.Data;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class LoginResponse {

    private int userId;
    private String token;
}
