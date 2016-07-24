package com.wowchina.dao;

import com.wowchina.domain.Industry;
import com.wowchina.domain.User;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class IndustryDao {

    @Autowired
    private SessionService sessionService;

    public List<Industry> queryIndustrysByIds(String[] ids){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.IndustryMapper.queryIndustrysByIds";
        List<Industry> industryList = session.selectList(statement, ids);
        session.close();
        return industryList;
    }

    public List<Industry> queryAllIndustry(){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.IndustryMapper.queryAllIndustry";
        List<Industry> industrys = session.selectList(statement);
        session.close();
        return industrys;
    }
}
