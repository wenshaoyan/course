package com.wenshao.dal;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.wenshao.dal.util.MongoDBUtil;
import org.apache.ibatis.io.Resources;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

/**
 * Created by wenshao on 2017/11/15.
 */
public class mongoTest {
    public static void main1(String[] args) {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress("123.207.55.204", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("course", "course", "19930314106".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String collName = "upvote";
        MongoCollection<Document> coll = MongoDBUtil.instance.getCollection(collName);
        MongoCursor<Document> cursor1 = coll.find(Filters.eq("course_id", "1")).iterator();
        while (cursor1.hasNext()) {
            org.bson.Document _doc = (Document) cursor1.next();
            System.out.println(_doc.toString());
        }

        BasicDBObject groupFields = new BasicDBObject("_id", "$course_id");
        groupFields.put("count", new BasicDBObject( "$sum", 1));
        BasicDBObject group = new BasicDBObject("$group", groupFields);

        ArrayList<Bson> bsons = new ArrayList<Bson>();
        bsons.add(group);
        AggregateIterable<Document> iterable = coll.aggregate(bsons);

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                String s = document.toJson();
                s="1";
                Gson gson = new Gson();

                A a = gson.fromJson(s, A.class);
                System.out.println(a.get_id());
            }
        });
    }
    private class A{
        private String _id;
        private String count;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

}
