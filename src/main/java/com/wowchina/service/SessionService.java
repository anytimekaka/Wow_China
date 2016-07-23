package com.wowchina.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangguisheng on 16/6/20.
 */
@Service
public class SessionService {

    private SqlSessionFactory sqlSessionFactory;

    public SessionService (){
        //mybatis的配置文件
        String resource = "mybatis-config.xml";
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建sqlSession的工厂
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    public SqlSessionFactory getSessionFactory(){
        return this.sqlSessionFactory;
    }

    public SqlSession getSession(){
        return this.sqlSessionFactory.openSession();
    }
}
