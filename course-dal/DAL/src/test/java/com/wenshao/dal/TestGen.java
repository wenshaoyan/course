package com.wenshao.dal;


import com.wenshao.dal.handler.BannerHandler;
import com.wenshao.dal.handler.DynamicCacheProxyHandler;
import com.wenshao.dal.server.Server;
import com.wenshao.dal.thriftgen.BannerService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.generator.api.ShellRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

/**
 * Created by wenshao on 2018/1/24.
 */
public class TestGen {
    public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\resources\\mybatis-generator-config.xml", "-overwrite" };
        ShellRunner.main(args);
    }
   /* public static void main1(String[] args) {
        try {
            String resource = "mybatis-user-auto-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sessionFactory.openSession();
            UserAutoMapper userAutoMapper = sqlSession.getMapper(UserAutoMapper.class);
            UserAutoExample a= new UserAutoExample();
            long i1= 1516766134757L;
            long i2= 1516766134757L;
            a.createCriteria()
                    .andUserIdBetween(1,10099)
            .andUserRegisterTimeBetween(new Date(i1),new Date(i2));
            a.setOrderByClause("user_name desc,user_id asc");

            List<UserAuto> list = userAutoMapper.selectByExample(a);
            for (UserAuto userAuto : list) {
                System.out.println(userAuto.getUserId());
                System.out.println(userAuto.getUserName());
            }
            sqlSession.commit();
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
