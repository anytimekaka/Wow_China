package com.wowchina.service;

import com.wowchina.dao.IndustryDao;
import com.wowchina.dao.MajorDao;
import com.wowchina.dao.UserDao;
import com.wowchina.domain.*;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.EditUserInfoRequest;
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
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IndustryDao industryDao;
    @Autowired
    private MajorDao majorDao;

    /**
     * check用户是否合法
     * @param userId
     * @param token
     * @return true:合法
     */
    public boolean checkUser(int userId, String token){
        User user = this.userDao.queryUserInfoByUserId(userId);
        return null != user && user.getToken().equals(token);
    }

    /**
     * 根据UserId查询用户基本信息
     * @param userId
     * @return
     */
    public CommonResponse<UserInfo> queryUserInfoByUserId(int userId){
        User user = this.userDao.queryUserInfoByUserId(userId);
        if(null != user){
            List<Industry> industryList = this.industryDao.queryIndustrysByIds(user.getIndustryids().split(","));
            Major major = this.majorDao.queryMajorById(user.getMajorid());
            UserInfo userInfo = new UserInfo();
            userInfo.setMajor(major);
            userInfo.setIndustrys(industryList);
            userInfo.setId(userId);
            userInfo.setCertificates(user.getCertificates());
            userInfo.setEmail(user.getEmail());
            userInfo.setFacebook(user.getFacebook());
            userInfo.setLinkedinid(user.getLinkedinid());
            userInfo.setLinkedinprofileurl(user.getLinkedinprofileurl());
            userInfo.setLinkedinusername(user.getLinkedinusername());
            userInfo.setLocation(user.getLocation());
            userInfo.setRealname(user.getRealname());
            userInfo.setSkills(user.getSkills());
            userInfo.setTel(user.getTel());
            userInfo.setUniversity(user.getUniversity());
            userInfo.setUserimage(user.getUserimage());

            CommonResponse<UserInfo> response = CommonResponse.successResponse();
            response.setResult(userInfo);

            return response;
        }
        return CommonResponse.errorResponse("no user with userId: " + userId);
    }

    /**
     * 更新用户图片信息
     * @param userId
     * @param userimage
     * @return
     */
    public CommonResponse updateUserImageInfo(int userId, String userimage){
        User user = new User();
        user.setId(userId);
        user.setUserimage(userimage);
        int count = this.userDao.updateUserImageInfo(user);
        if(count > 0){
            return CommonResponse.successResponse("update userInfo success");
        }
        return CommonResponse.errorResponse("update userInfo error");
    }

    /**
     * 更新用户资料信息
     * @param request
     * @return
     */
    public CommonResponse updateUserInfo(EditUserInfoRequest request){
        User user = new User();
        user.setId(request.getUserId());
        BeanUtils.copyProperties(request, user);
        int count = this.userDao.updateUserInfo(user);
        if(count > 0){
            return CommonResponse.successResponse("update userInfo success");
        }
        return CommonResponse.errorResponse("update userInfo error");
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public CommonResponse login(User user){
        CommonResponse response = new CommonResponse(0,"success");
        user.setPassword(Parse2MD5.parseStrToMd5L32(user.getPassword()));
        User responseUser = this.userDao.login(user);
        if(null != responseUser){
            response.setMessage("login success");
            LoginResponse result = new LoginResponse();
            result.setUserId(responseUser.getId());
            result.setToken(responseUser.getToken());
            response.setResult(result);
        }else{
            response.setCode(1);
            response.setMessage("wrong username or password");
        }
        return response;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public CommonResponse register(User user){
        CommonResponse response = new CommonResponse(0,"success");
        if(this.userDao.checkUsernameIsExist(user)){
            response.setCode(1);
            response.setMessage("username has been used");//用户名已被使用
            return response;
        }
        String passwordmd5 = Parse2MD5.parseStrToMd5L32(user.getPassword());
        String token = Parse2MD5.parseStrToMd5L32(user.getUsername() + user.getPassword());
        user.setPassword(passwordmd5);
        user.setToken(token);
        int userId = this.userDao.addUser(user);
        UserRegisterInfo userRegisterInfo = new UserRegisterInfo();
        userRegisterInfo.setUserId(userId);
        userRegisterInfo.setToken(token);
        response.setResult(userRegisterInfo);
        return response;
    }
}
