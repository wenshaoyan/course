package com.wenshao.dal.handler;

import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.dao.impl.UserDaoImpl;
import com.wenshao.dal.thriftgen.User;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by wenshao on 2017/8/28.
 * 用户handler
 */
public class UserHandler implements UserService.Iface {
    private UserDaoImpl userDao;
    private static Logger logger = Logger.getLogger(String.valueOf(UserHandler.class));


    public UserHandler(SqlSessionFactory _sessionFactory) {
        userDao = new UserDaoImpl(_sessionFactory);

    }
    @Override
    public int userInsert(User user) throws TException {
        UserBean userBean = new UserBean(user);
        Date date = new Date();
        long time = date.getTime();
        try {
            userBean.setCreate_time(String.valueOf(time));
            return userDao.insert(userBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public User userFindById(int id) throws TException {
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<User> userSelectAll() throws TException {

        try {
            List<User> users = new ArrayList<User>();
            List<UserBean> bannerBeans = userDao.selectAll();
            for (UserBean bean : bannerBeans) {
                users.add((User) bean);

            }
            return users;
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<User> userSelect(User user) throws TException {
        List<User> users = new ArrayList<User>();
        UserBean paramsBean = new UserBean(user);
        try {
            List<UserBean> userBeans = userDao.select(paramsBean);
            for (UserBean bean : userBeans) {
                users.add((User) bean);

            }
            return users;
        } catch (Exception e) {
            throw new TException(e);
        }
    }



}
