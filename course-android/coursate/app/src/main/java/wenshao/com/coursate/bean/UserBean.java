package wenshao.com.coursate.bean;

/**
 * Created by wenshao on 2017/9/23.
 * 用户对象
 */

public class UserBean {
    public int id;
    public String name;
    public String head;
    public String tel;
    public String device_uuid;
    public String register_time;
    public String create_time;
    public String update_time;
    public String password;

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", tel='" + tel + '\'' +
                ", device_uuid='" + device_uuid + '\'' +
                ", register_time='" + register_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
