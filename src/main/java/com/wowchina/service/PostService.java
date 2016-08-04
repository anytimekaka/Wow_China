package com.wowchina.service;

import com.wowchina.dao.PostDao;
import com.wowchina.dao.UserDao;
import com.wowchina.domain.Post;
import com.wowchina.domain.User;
import com.wowchina.model.AddPostRequest;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.GetPostInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;

    /**
     * 根据Post id查询post的信息
     * @param id
     * @return
     */
    public CommonResponse<GetPostInfoResponse> getPostInfoById(int id){
        Post post = postDao.getPostInfoById(id);
        User user = new User();
        if(null != post){
            userDao.queryUserInfoByUserId(post.getUserid());
        }
        GetPostInfoResponse getPostInfoResponse = new GetPostInfoResponse();
        getPostInfoResponse.setPost(post);
        getPostInfoResponse.setUser(user);
        CommonResponse commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(getPostInfoResponse);
        return commonResponse;
    }

    public CommonResponse<Integer> addPost(AddPostRequest request){
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        int postId = postDao.addPost(post);
        CommonResponse commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(postId);
        return commonResponse;
    }

}
