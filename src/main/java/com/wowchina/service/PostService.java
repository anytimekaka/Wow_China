package com.wowchina.service;

import com.wowchina.dao.IndustryDao;
import com.wowchina.dao.MajorDao;
import com.wowchina.dao.PostDao;
import com.wowchina.dao.UserDao;
import com.wowchina.domain.Industry;
import com.wowchina.domain.Major;
import com.wowchina.domain.Post;
import com.wowchina.domain.User;
import com.wowchina.model.AddPostRequest;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.GetPostInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private IndustryDao industryDao;

    /**
     * 根据Post id查询post的信息
     * @param id
     * @return
     */
    public CommonResponse<GetPostInfoResponse> getPostInfoById(int id){
        Post post = postDao.getPostInfoById(id);
        User user = new User();
        List<Major> majors = new ArrayList<Major>();
        List<Industry> industrys = new ArrayList<Industry>();
        if(null != post){
            userDao.queryUserInfoByUserId(post.getUserid());
            industrys = industryDao.queryIndustrysByIds(String.valueOf(post.getIndustryid()).split(","));
            majors = majorDao.queryMajorsByIds(post.getOpento());
        }
        GetPostInfoResponse getPostInfoResponse = new GetPostInfoResponse();
        getPostInfoResponse.setPost(post);
        getPostInfoResponse.setUser(user);
        getPostInfoResponse.setOpenToMajors(majors);
        getPostInfoResponse.setIndustry(industrys.get(industrys.size()-1));
        CommonResponse commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(getPostInfoResponse);
        return commonResponse;
    }

    public CommonResponse<Integer> addPost(AddPostRequest request){
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        post.setUserid(request.getUserId());
        int postId = postDao.addPost(post);
        CommonResponse commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(postId);
        return commonResponse;
    }

}
