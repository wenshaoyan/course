namespace java com.wenshao.dal.thriftgen

include "bean.thrift"

service UserService{
    i32 insert(1: bean.User user)
    bean.User findUserById(1: i32 id)
    list<bean.User> findUserByLogin(1: bean.User user)
    list<bean.User> findUserByTel(1: string tel)
}
service BannerService{
    i32 insert(1: bean.Banner banner)
    i32 update(1: bean.Banner banner)
    i32 remove(1: i32 id)
    list<bean.Banner> selectAll()
    list<bean.Banner> select(1: bean.Banner banner)


}