package com.wenshao.dal.bean;

import com.wenshao.dal.thriftgen.User;

import java.sql.Timestamp;
/**
 * Created by wenshao on 2017/8/28.
 * 用户类接口
 */
public class UserBean {
    private int id;
    private String name;
    private String head;
    private String tel;
    private String device_uuid;
    private Timestamp register_time;
    private Timestamp create_time;
    private Timestamp update_time;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDevice_uuid() {
        return device_uuid;
    }

    public void setDevice_uuid(String device_uuid) {
        this.device_uuid = device_uuid;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", tel='" + tel + '\'' +
                ", device_uuid='" + device_uuid + '\'' +
                ", register_time=" + register_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
    public User toUser(){
        User user = new User();
        user.id = (short) this.id;
        user.name = name;
        user.head = head;
        user.tel = tel;
        user.device_uuid = device_uuid;
        user.register_time = register_time.toString();
        user.create_time = create_time.toString();
        user.update_time = update_time.toString();
        return user;
    }
}
