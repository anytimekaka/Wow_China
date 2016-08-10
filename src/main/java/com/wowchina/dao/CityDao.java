package com.wowchina.dao;

import com.wowchina.domain.City;
import com.wowchina.service.SessionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/20.
 */

@Service
public class CityDao {

    @Autowired
    private SessionService sessionService;

    public City queryCityById(int cityId){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.CityMapper.queryCityById";
        City city = session.selectOne(statement, cityId);
        session.close();
        return city;
    }

    public List<City> queryAllCity(){
        SqlSession session = this.sessionService.getSession();
        String statement = "com.wowchina.domain.CityMapper.queryAllCity";
        List<City> citys = session.selectList(statement);
        session.close();
        return citys;
    }
}
