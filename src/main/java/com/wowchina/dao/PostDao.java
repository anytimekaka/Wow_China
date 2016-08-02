package com.wowchina.dao;

import com.wowchina.domain.Post;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class PostDao {

    @Autowired
    private SessionService sessionService;

    public Post getPostInfoById(int id){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.PostMapper.getPostInfoById";
        Post post = session.selectOne(statement, id);
        session.close();
        return post;
    }

    public boolean addPost(Post post){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.PostMapper.addPost";
        int count = session.insert(statement, post);
        session.commit();
        session.close();
        if(1 == count){
            return true;
        }
        return false;
    }
}
