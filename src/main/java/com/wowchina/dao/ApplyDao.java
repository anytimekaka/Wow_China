package com.wowchina.dao;

import com.wowchina.domain.Apply;
import com.wowchina.domain.Message;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class ApplyDao {

    @Autowired
    private SessionService sessionService;

    public int addApply(Apply apply){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.ApplyMapper.addApply";
        int messageId = session.insert(statement, apply);
        session.commit();
        session.close();
        return messageId;
    }

    public boolean isUserApplied(Apply apply){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.ApplyMapper.isUserApplied";
        int count = session.selectOne(statement, apply);
        session.close();
        return count>0;
    }

}
