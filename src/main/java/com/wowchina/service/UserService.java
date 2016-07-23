package com.wowchina.service;

import com.wowchina.dao.UserDao;
import com.wowchina.domain.User;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.LoginResponse;
import com.wowchina.util.Parse2MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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
        if(this.userDao.addUser(user)){
            response.setMessage("register success");
            response.setResult(token);
            return response;
        }else{
            response.setCode(1);
            response.setMessage("register error");
        }
        return response;
    }
}
