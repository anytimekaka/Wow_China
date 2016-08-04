package com.wowchina.dao;

import com.wowchina.domain.Industry;
import com.wowchina.domain.Major;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class MajorDao {

    @Autowired
    private SessionService sessionService;

    public Major queryMajorById(int majorId){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MajorMapper.queryMajorById";
        Major major = session.selectOne(statement, majorId);
        session.close();
        return major;
    }

    public List<Major> queryAllMajor(){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.MajorMapper.queryAllMajor";
        List<Major> majors = session.selectList(statement);
        session.close();
        return majors;
    }
}