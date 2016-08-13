package com.wowchina.dao;

import com.wowchina.domain.Post;
import com.wowchina.service.SessionService;
import com.wowchina.util.TimeUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public int addPost(Post post){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.PostMapper.addPost";
        session.insert(statement, post);
        int id = post.getId();
        session.commit();
        session.close();
        if(1 <= id){
            return id;
        }
        return 0;
    }

    public int updatePost(Post post){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.PostMapper.updatePost";
        int affectRow = session.update(statement, post);
        session.commit();
        session.close();
        return affectRow;
    }
}
