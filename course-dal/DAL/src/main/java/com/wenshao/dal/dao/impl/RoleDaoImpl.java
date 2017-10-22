package com.wenshao.dal.dao.impl;

import com.wenshao.dal.bean.RoleBean;
import com.wenshao.dal.dao.RoleDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wenshao on 2017/10/21.
 * 角色对象实现类
 */
public class RoleDaoImpl implements RoleDao{
    private SqlSessionFactory sqlSessionFactory;
    private static final String sqlTag = "mapper.roleMapper";
    public RoleDaoImpl(SqlSessionFactory sqlSessionFactory){
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }
    @Override
    public int insert(RoleBean roleBean) throws Exception {
        return 0;
    }

    @Override
    public RoleBean findById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleBean roleBean1 = new RoleBean();
        roleBean1.setId(id);
        List<RoleBean> userBeans  = sqlSession.selectList(sqlTag + ".select", roleBean1);
        RoleBean roleBean;
        if (userBeans.size()==0){
            roleBean = new RoleBean();
        }else{
            roleBean = userBeans.get(0);
        }
        sqlSession.commit();
        sqlSession.close();
        return roleBean;
    }

    @Override
    public List<RoleBean> select(RoleBean roleBean) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<RoleBean> roleBeans = sqlSession.selectList(sqlTag + ".select", roleBean);
        sqlSession.commit();
        sqlSession.close();
        return roleBeans;
    }

    @Override
    public List<RoleBean> selectAll() throws Exception {
        return null;
    }
}
