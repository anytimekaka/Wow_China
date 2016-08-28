package com.wowchina.dao;

import com.wowchina.domain.Message;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class MessageDao {

    @Autowired
    private SessionService sessionService;

    public int addMessage(Message message){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.addMessage";
        session.insert(statement, message);
        int messageId = message.getId();
        session.commit();
        session.close();
        return messageId;
    }

    public List<Message> queryMessageByUserId(int userId){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.queryMessageByUserId";
        List<Message> messages = session.selectList(statement, userId);
        session.close();
        return messages;
    }

    public List<Message> queryMessageByUserIdAndStatus(int userId, List<Integer> status){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", status);
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.queryMessageByUserIdAndStatus";
        List<Message> messages = session.selectList(statement, paramMap);
        session.close();
        return messages;
    }

    public void updateMessageById(Message message){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MessageMapper.updateMessageById";
        session.update(statement, message);
        session.commit();
        session.close();
    }
}
