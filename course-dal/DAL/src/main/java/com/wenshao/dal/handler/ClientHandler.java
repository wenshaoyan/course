package com.wenshao.dal.handler;

import com.wenshao.dal.bean.ClientBean;
import com.wenshao.dal.dao.ClientDao;
import com.wenshao.dal.dao.impl.BannerDaoImpl;
import com.wenshao.dal.dao.impl.ClientDaoImpl;
import com.wenshao.dal.thriftgen.ClientSide;
import com.wenshao.dal.thriftgen.ClientService;
import com.wenshao.dal.thriftgen.ClientVersion;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wenshao on 2017/10/7.
 * 客户端管理
 */
public class ClientHandler implements ClientService.Iface{
    private ClientDao clientDao;
    private static Logger logger = Logger.getLogger("ClientHandler");

    public ClientHandler(SqlSessionFactory _sessionFactory) {
        clientDao = new ClientDaoImpl(_sessionFactory);

    }
    @Override
    public int clientInsert(ClientSide clientSide) throws TException {
        return 0;
    }

    @Override
    public int clientUpdate(ClientSide clientSide) throws TException {
        return 0;
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
        List<ClientSide> banners = new ArrayList<ClientSide>();
        ClientBean paramsBean = new ClientBean(clientSide);
        try {
            List<ClientBean> clientBeans = clientDao.clientSelect(paramsBean);
            for (ClientBean bean : clientBeans) {
                banners.add((ClientSide) bean);
            }
            return banners;
        } catch (Exception e) {
            throw new TException(e);
        }
    }

    @Override
    public int versionInsert(ClientVersion clientVersion) throws TException {
        return 0;
    }

    @Override
    public int versionUpdate(ClientVersion clientVersion) throws TException {
        return 0;
    }

    @Override
    public int versionRemove(int id) throws TException {
        return 0;
    }

    @Override
    public ClientVersion versionFindById(int id) throws TException {
        return null;
    }

    @Override
    public List<ClientVersion> versionSelectAll() throws TException {
        return null;
    }

    @Override
    public List<ClientVersion> versionSelect(ClientVersion clientVersion) throws TException {
        return null;
    }
}
