namespace java com.wenshao.dal.thriftgen

include "bean.thrift"

service UserService{
     i32 insert(1: bean.User user)
     bean.User findUserById(1: i16 id)
     list<bean.User> findUserByLogin(1: bean.User user)
     list<bean.User> findUserByTel(1: string tel)
}
service MessageService{
     i32 isMessage()
}