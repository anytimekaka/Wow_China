package com.wowchina.dao;

import com.wowchina.domain.MajorPostMap;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class MajorPostMapDao {

    @Autowired
    private SessionService sessionService;

    public int addMajorPostMap(MajorPostMap majorPostMap){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MajorPostMapMapper.addMajorPostMap";
        int id = session.insert(statement, majorPostMap);
        session.commit();
        session.close();
        return id;
    }

}
