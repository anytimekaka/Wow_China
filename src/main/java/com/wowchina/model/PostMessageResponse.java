package com.wowchina.model;

import com.wowchina.domain.Message;
import lombok.Data;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class PostMessageResponse {

    private int status;
    private String desc;

    private List<Message> messages;
}
