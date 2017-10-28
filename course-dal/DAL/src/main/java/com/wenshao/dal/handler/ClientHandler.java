package com.wenshao.dal.handler;

import com.wenshao.dal.bean.ClientBean;
import com.wenshao.dal.bean.VersionBean;
import com.wenshao.dal.bean.QueryBean;
import com.wenshao.dal.dao.ClientDao;
import com.wenshao.dal.dao.UserDao;
import com.wenshao.dal.dao.VersionDao;
import com.wenshao.dal.dao.impl.ClientDaoImpl;
import com.wenshao.dal.dao.impl.VersionDaoImpl;
import com.wenshao.dal.thriftgen.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;
import com.wenshao.dal.thriftgen.Version;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wenshao on 2017/10/7.
 * 客户端管理
 */
public class ClientHandler implements ClientService.Iface {
    private ClientDao clientDao;
    private VersionDao versionDao;
    private static Logger logger = Logger.getLogger("ClientHandler");

    public ClientHandler(SqlSessionFactory _sessionFactory) {
        clientDao = new ClientDaoImpl(_sessionFactory);
        versionDao = new VersionDaoImpl(_sessionFactory);

    }

    private List<ClientSide> clientQuery(ClientBean paramsBean) throws Exception {
        List<ClientSide> results = new ArrayList<ClientSide>();
        List<ClientBean> beans = clientDao.select(paramsBean);
        for (ClientBean bean : beans) {
            List<VersionBean> versionBeans = bean.getVersionBeans();
            List<Version> versions = new ArrayList<Version>();
            for (VersionBean versionBean : versionBeans) {
                versions.add(versionBean);
            }
            bean.setVersions(versions);
            results.add(bean);
        }
        return results;
    }

    private List<Version> versionQuery(VersionBean paramsBean) throws Exception {
        List<Version> results = new ArrayList<Version>();
        List<VersionBean> beans = versionDao.select(paramsBean);
        for (VersionBean bean : beans) {
            results.add((Version) bean);
        }
        return results;
    }

    @Override
    public int clientInsert(ClientSide clientSide) throws TException {
        try {
            return clientDao.insert(new ClientBean(clientSide));
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int clientUpdate(ClientSide clientSide) throws TException {
        try {
            return clientDao.update(new ClientBean(clientSide));
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int clientRemove(int id) throws TException {
        return 0;
    }

    @Override
    public ClientSide clientFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<ClientSide> clientSelectAll() throws TException {
        return null;
    }

    @Override
    public List<ClientSide> clientSelect(ClientSide clientSide) throws TException {
        try {
            return clientQuery(new ClientBean(clientSide));
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<ClientSide> clientSelectQuery(ClientSide clientSide, Query query) throws TException {

        ClientBean clientBean = new ClientBean(clientSide);
        QueryBean queryBean = new QueryBean(query, ClientBean.TABLE_PREFIX);
        clientBean.setQueryBean(queryBean);
        try {
            return clientQuery(clientBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<ClientSide> clientSelectCustom(ClientSide clientSide, Custom custom) throws TException {
        ClientBean clientBean = new ClientBean(clientSide);
        clientBean.setTables(custom.getTables());
        try {
            return clientQuery(clientBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<ClientSide> clientSelectQueryCustom(ClientSide clientSide, Query query, Custom custom) throws TException {
        ClientBean clientBean = new ClientBean(clientSide);
        QueryBean queryBean = new QueryBean(query, ClientBean.TABLE_PREFIX);
        clientBean.setQueryBean(queryBean);
        clientBean.setTables(custom.getTables());

        try {
            return clientQuery(clientBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int versionInsert(Version version) throws TException {
        return 0;
    }

    @Override
    public int versionUpdate(Version clientVersion) throws TException {
        return 0;
    }

    @Override
    public int versionRemove(int id) throws TException {
        return 0;
    }

    @Override
    public Version versionFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<Version> versionSelectAll() throws TException {
        return null;
    }

    @Override
    public List<Version> versionSelect(Version version) throws TException {
        try {
            return versionQuery(new VersionBean(version));
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public List<Version> versionSelectQuery(Version version, Query query) throws TException {
        VersionBean clientVersionBean = new VersionBean(version);
        QueryBean queryBean = new QueryBean(query, VersionBean.TABLE_PREFIX);
        System.out.println(version);
        clientVersionBean.setQueryBean(queryBean);
        try {
            return versionQuery(clientVersionBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int versionCountSelectQuery(Version version, Query query) throws TException {
        QueryBean queryBean = new QueryBean(query);
        VersionBean paramsBean = new VersionBean(version);
        paramsBean.setQueryBean(queryBean);
        try {
            return versionDao.count(paramsBean);
        } catch (Exception e) {
            throw new TException(e);
        }
    }
}
