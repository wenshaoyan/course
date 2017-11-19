package com.wenshao.dal.util;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.apache.ibatis.io.Resources;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.bson.Document;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了
 * 注意Mongo已经实现了连接池，并且是线程安全的。
 * 设计为单例模式， 因 MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可，
 * Mongo有个内置的连接池（默认为10个） 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时，
 * DB和DBCollection是绝对线程安全的
 * link:https://www.cnblogs.com/yisheng163/p/6580733.html
 */
public enum MongoDBUtil {
    /**
     * 定义一个枚举的元素，它代表此类的一个实例
     */
    instance;
    private MongoClient mongoClient;
    private String dbName;

    static {
        System.out.println("===============MongoDBUtil初始化========================");
        String ip = "127.0.0.1";
        int port = 27017;
        String username = null;
        String password = null;
        String dbName = null;
        // 从配置文件中获取属性值
        Properties properties = new Properties();
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mongodb.properties");
            properties.load(reader);// 加载属性文件
            String tIp = properties.getProperty("ip");
            int tPort = Integer.parseInt(properties.getProperty("port"));
            String tUsername = properties.getProperty("username");
            String tPassword = properties.getProperty("password");
            String tDb = properties.getProperty("db");
            if (tIp != null) ip = tIp;
            if (tPort != 0) port = tPort;
            if (tUsername != null) username = tUsername;
            if (tPassword != null) password = tPassword;
            if (tDb != null) dbName = tDb;
            instance.dbName = dbName;
        } catch (IOException io) {
            io.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ServerAddress serverAddress = new ServerAddress(ip,port);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential = MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);




        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(20);// 连接池设置最大为300个连接,默认为100
        options.connectTimeout(15000);// 连接超时，推荐>3000毫秒
        options.maxWaitTime(5000); //
        options.socketTimeout(0);// 套接字超时时间，0无限制
        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        options.writeConcern(WriteConcern.MAJORITY);//
        MongoClientOptions build = options.build();
        //通过连接认证获取MongoDB连接
        instance.mongoClient = new MongoClient(addrs,credentials,build);
    }
    // -----------------------------------共用方法---------------------------------------------------
    /**
     * 获取DB实例 - 指定DB
     *
     * @param dbName    数据库名
     * @return          MongoDatabase对象
     */
    private MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            return mongoClient.getDatabase(dbName);
        }
        return null;
    }

    /**
     * 获取collection对象 - 指定Collection
     *
     * @param collName      集合名称
     * @return              集合
     */
    public MongoCollection<Document> getCollection(String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        return mongoClient.getDatabase(dbName).getCollection(collName);
    }

    /**
     * 返回库下的所有集合名称
     * @return      集合名称列表
     */
    public List<String> getAllCollections() {
        MongoIterable<String> cols = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : cols) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * 获取所有数据库名称列表
     *
     */
    private MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除一个数据库
     */
    private void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * 查找对象 - 根据主键_id
     *
     * @param coll      集合对象
     * @param id        id
     * @return          Document对象
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idObj = null;
        try {
            _idObj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        return coll.find(Filters.eq("_id", _idObj)).first();
    }

    /** 统计数 */
    public int getCount(MongoCollection<Document> coll) {
        return (int) coll.count();
    }

    /** 条件查询 */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /** 分页查询 */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }

    /**
     * 通过ID删除
     *
     * @param coll      集合
     * @param id        id
     * @return          删除的条数
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * FIXME
     *
     * @param coll          集合
     * @param id            id
     * @param newDoc        新的集合
     * @return              新的集合
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newDoc) {
        ObjectId _idObj = null;
        try {
            _idObj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idObj);
        // coll.replaceOne(filter, newDoc); // 完全替代
        coll.updateOne(filter, new Document("$set", newDoc));
        return newDoc;
    }

    /**
     * 删除集合
     * @param collName      集和名称
     */
    public void dropCollection(String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * 关闭Mongodb
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
        System.out.println("===============MongoDBUtil关闭连接========================");
    }
}
