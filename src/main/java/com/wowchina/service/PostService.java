package com.wowchina.service;

import com.wowchina.dao.*;
import com.wowchina.domain.*;
import com.wowchina.model.AddPostRequest;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.GetPostInfoResponse;
import com.wowchina.util.TimeUtils;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    private MajorPostMapDao majorPostMapDao;

    public CommonResponse<List<Post>> getPostListByUserId(int userId){
        CommonResponse<List<Post>> response = CommonResponse.successResponse();
        List<Post> list = this.postDao.getPostInfoByUserId(userId);
        response.setResult(list);
        return response;
    }

    /**
     * 判断此userId是否是此post的作者
     * @param userId
     * @param postId
     * @return
     */
    public boolean isUserPoster(int userId, int postId){
        Post post = postDao.getPostInfoById(postId);
        return userId == post.getUserid();
    }

    /**
     * 根据Post id查询post的信息
     * @param id
     * @return
     */
    public CommonResponse<GetPostInfoResponse> getPostInfoById(int id){
        Post post = postDao.getPostInfoById(id);
        UserBaseInfo user = new UserBaseInfo();
        List<Major> majors = new ArrayList<Major>();
        List<Industry> industrys = new ArrayList<Industry>();
        if(null != post){
            user = userDao.queryUserBaseInfoByUserId(post.getUserid());
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
        if(StringUtils.isNotEmpty(request.getOpento())){
            String[] majorIds = request.getOpento().split(",");
            for(String majorId : majorIds){
                majorPostMapDao.addMajorPostMap(new MajorPostMap(Integer.parseInt(majorId), postId));
            }
        }
        CommonResponse commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(postId);
        return commonResponse;
    }

    public CommonResponse<String> updatePost(AddPostRequest request){
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        post.setUserid(request.getUserId());
        postDao.updatePost(post);
        CommonResponse commonResponse = CommonResponse.successResponse();
        return commonResponse;
    }

    public CommonResponse<String> updatePost(Post post){
        postDao.updatePost(post);
        CommonResponse commonResponse = CommonResponse.successResponse();
        return commonResponse;
    }

}
