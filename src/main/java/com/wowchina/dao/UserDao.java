package com.wowchina.dao;

import com.wowchina.domain.User;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class UserDao {

    @Autowired
    private SessionService sessionService;

    public User queryUserInfoByToken(String token){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.queryUserInfoByToken";
        User resultUser = session.selectOne(statement, token);
        session.close();
        return resultUser;
    }

    public User queryUserInfoByUserId(int userId){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.queryUserInfoByUserId";
        User resultUser = session.selectOne(statement, userId);
        session.close();
        return resultUser;
    }

    public int updateUserInfo(User user){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.updateUserInfo";
        int count = session.update(statement, user);
        session.commit();
        session.close();
        return count;
    }

    public int updateUserImageInfo(User user){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.updateUserImageInfo";
        int count = session.update(statement, user);
        session.commit();
        session.close();
        return count;
    }

    /**
     * 登录dao
     * @param user
     * @return null：失败
     */
    public User login(User user){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.login";
        User resultUser = session.selectOne(statement, user);
        session.close();
        return resultUser;
    }

    /**
     * 检查用户名是否已注册
     * @param user
     * @return true：已注册
     */
    public boolean checkUsernameIsExist(User user){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.checkUsernameIsExist";
        int count = session.selectOne(statement, user);
        session.close();
        if(1 == count){
            return true;
        }
        return false;
    }

    /**
     * 添加用户
     * @param user
     * @return true：添加成功
     */
    public boolean addUser(User user){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.UserMapper.addUser";
        int count = session.insert(statement, user);
        session.commit();
        session.close();
        if(1 == count){
            return true;
        }
        return false;
    }
}
