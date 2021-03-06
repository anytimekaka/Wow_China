package com.wowchina.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wowchina.domain.User;

public class Test1 {

	public static void main(String[] args) throws IOException {
        //mybatis的配置文件
        String resource = "mybatis-config.xml";
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Resources.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * com.wowchina.domain.UserMapper是UserMapper.xml文件中mapper标签的namespace属性的值，
         * selectUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
//        String statement = "com.wowchina.domain.UserMapper.selectUser";//映射sql的标识字符串
//        执行查询返回一个唯一user对象的sql
//        User user = session.selectOne(statement, 3);
//        System.out.println(user.getName());
    }
}
