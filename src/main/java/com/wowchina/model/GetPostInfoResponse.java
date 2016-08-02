package com.wowchina.model;

import com.wowchina.domain.Post;
import com.wowchina.domain.User;
import lombok.Data;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class GetPostInfoResponse {

    private User user;
    private Post post;
}
