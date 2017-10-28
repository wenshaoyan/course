package com.wenshao.dal.handler;

import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.bean.RoleBean;
import com.wenshao.dal.bean.UserBean;
import com.wenshao.dal.dao.RoleDao;
import com.wenshao.dal.dao.impl.RoleDaoImpl;
import com.wenshao.dal.dao.impl.UserDaoImpl;
import com.wenshao.dal.thriftgen.Query;
import com.wenshao.dal.thriftgen.Role;
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
    private RoleDaoImpl roleDao;
    private static Logger logger = Logger.getLogger(String.valueOf(UserHandler.class));


    public UserHandler(SqlSessionFactory _sessionFactory) {
        userDao = new UserDaoImpl(_sessionFactory);
        roleDao = new RoleDaoImpl(_sessionFactory);
    }

    private List<User> query(UserBean paramsBean) throws Exception {
        List<User> users = new ArrayList<User>();
        List<UserBean> userBeans = userDao.select(paramsBean);
        for (UserBean bean : userBeans) {
            users.add((User) bean);
        }
        return users;
    }

    @Override
    public int userInsert(User user) throws TException {
        UserBean userBean = new UserBean(user);
        Date date = new Date();
        long time = date.getTime();
        try {
            userBean.setCreate_time(String.valueOf(time));
            userBean.setUpdate_time(String.valueOf(time));
            userBean.setRegister_time(String.valueOf(time));
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
        UserBean paramsBean = new UserBean(user);
        try {
            return query(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<User> userQuery(Query query) throws TException {
        QueryBean queryBean = new QueryBean(query, UserBean.TABLE_PREFIX);
        UserBean paramsBean = new UserBean();
        paramsBean.setQueryBean(queryBean);
        try {
            return query(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<User> userSelectQuery(User user, Query query) throws TException {
        QueryBean queryBean = new QueryBean(query, UserBean.TABLE_PREFIX);
        UserBean paramsBean = new UserBean(user);
        paramsBean.setQueryBean(queryBean);
        try {
            return query(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int userCountSelectQuery(User user, Query query) throws TException {
        QueryBean queryBean = new QueryBean(query, UserBean.TABLE_PREFIX);
        UserBean paramsBean = new UserBean(user);
        paramsBean.setQueryBean(queryBean);
        try {
            return userDao.count(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int roleInsert(Role role) throws TException {
        return 0;
    }

    @Override
    public Role roleFindById(int id) throws TException {
        try {
            return roleDao.findById(id);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Role> roleSelectAll() throws TException {
        List<Role> roles = new ArrayList<Role>();
        try {
            List<RoleBean> roleBeans = roleDao.selectAll();
            for (RoleBean bean : roleBeans) {
                roles.add((Role) bean);
            }
            return roles;
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Role> roleSelect(Role role) throws TException {
        List<Role> roles = new ArrayList<Role>();
        RoleBean paramsBean = new RoleBean(role);
        try {
            List<RoleBean> roleBeans = roleDao.select(paramsBean);
            for (RoleBean bean : roleBeans) {
                roles.add((Role) bean);

            }
            return roles;
        } catch (Exception e) {
            throw new TException(e);
        }
    }


}
