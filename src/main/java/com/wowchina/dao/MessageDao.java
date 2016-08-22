package com.wowchina.dao;

import com.wowchina.domain.Message;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class MessageDao {

    @Autowired
    private SessionService sessionService;

    public void addMessage(Message message){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.addMessage";
        session.insert(statement, message);
        session.commit();
        session.close();
    }

    public void updateMessagetoReadedById(int id){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.updateMessagetoReadedById";
        session.update(statement, id);
        session.commit();
        session.close();
    }

    public void updateMessagetoReadedByUserId(int userid){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.updateMessagetoReadedByUserId";
        session.update(statement, userid);
        session.commit();
        session.close();
    }

    public int getUnreadMessageCountByUserId(int userId){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.getUnreadMessageCountByUserId";
        int count = session.selectOne(statement, userId);
        session.close();
        return count;
    }

}
