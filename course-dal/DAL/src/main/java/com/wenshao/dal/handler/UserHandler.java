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

    public int insert(User user) throws TException {
        UserBean userBean = new UserBean();
        Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        try {
            userBean.setName(user.name);
            userBean.setHead(user.head);
            userBean.setTel(user.tel);
            userBean.setDevice_uuid(user.device_uuid);
            userBean.setRegister_time(timestamp);
            userBean.setCreate_time(timestamp);
            userBean.setUpdate_time(timestamp);
            userBean.setPassword(user.password);
            int id = userDao.insertUser(userBean);
            return id;
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }

    public User findUserById(int id) throws TException {
        try {
            UserBean userBeans = userDao.queryUserById(id);
            return userBeans.toUser();
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    public List<User> findUserByLogin(User user) throws TException {
        UserBean userBean = new UserBean();
        userBean.setPassword(user.password);
        userBean.setTel(user.tel);
        try {
            ArrayList<User> users = new ArrayList<User>();
            List<UserBean> userBeans = userDao.queryUserByInfo(userBean);
            return this.toListUser(users,userBeans);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    public List<User> findUserByTel(String tel) throws TException {
        try {
            ArrayList<User> users = new ArrayList<User>();
            List<UserBean> userBeans = userDao.queryUserByTel(tel);
            return this.toListUser(users,userBeans);
        } catch (Exception e) {
            throw new TException(e);
        }
    }
    private List<User> toListUser(List<User> users,List<UserBean> userBeans){
        for (UserBean bean : userBeans){
            users.add(bean.toUser());
        }
        return users;
    }


}
