package com.wowchina.service;

import com.wowchina.dao.IndustryDao;
import com.wowchina.dao.MajorDao;
import com.wowchina.dao.PostDao;
import com.wowchina.dao.UserDao;
import com.wowchina.domain.*;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.EditUserInfoRequest;
import com.wowchina.model.GetPostInfoResponse;
import com.wowchina.model.LoginResponse;
import com.wowchina.util.Parse2MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class InfoQueryService {

    @Autowired
    private IndustryDao industryDao;
    @Autowired
    private MajorDao majorDao;
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

    /**
     * 查询所有Major信息
     * @return
     */
    public CommonResponse<List<Major>> queryAllMajor(){
        List<Major> majors = this.majorDao.queryAllMajor();
        CommonResponse<List<Major>> commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(majors);
        return commonResponse;
    }

    /**
     * 查询所有Industry信息
     * @return
     */
    public CommonResponse<List<Industry>> queryAllIndustry(){
        List<Industry> industrys = this.industryDao.queryAllIndustry();
        CommonResponse<List<Industry>> commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(industrys);
        return commonResponse;
    }

}
