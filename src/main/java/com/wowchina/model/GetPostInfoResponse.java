package com.wowchina.model;

import com.wowchina.domain.Industry;
import com.wowchina.domain.Major;
import com.wowchina.domain.Post;
import com.wowchina.domain.UserBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/24.
 */
@Data
public class GetPostInfoResponse {

    private UserBaseInfo user;
    private Post post;
    private Industry industry;
    private List<Major> openToMajors;
}
