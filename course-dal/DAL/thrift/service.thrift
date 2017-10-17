namespace java com.wenshao.dal.thriftgen

include "bean.thrift"

service UserService{
    i32 userInsert(1: bean.User user),
    bean.User userFindById(1: i32 id),
    list<bean.User> userSelectAll(),
    list<bean.User> userSelect(1: bean.User user)
}
service BannerService{
    i32 insert(1: bean.Banner banner),
    i32 update(1: bean.Banner banner),
    i32 remove(1: i32 id),
    bean.Banner findById(1: i32 id),
    list<bean.Banner> selectAll(),
    list<bean.Banner> select(1: bean.Banner banner)
}
service ClientService{
    i32 clientInsert(1: bean.ClientSide clientSide),
    i32 clientUpdate(1: bean.ClientSide clientSide),
    i32 clientRemove(1: i32 id),
    bean.ClientSide clientFindById(1: i32 id),
    list<bean.ClientSide> clientSelectAll(),
    list<bean.ClientSide> clientSelect(1: bean.ClientSide clientSide),

    i32 versionInsert(1: bean.ClientVersion clientVersion),
    i32 versionUpdate(1: bean.ClientVersion clientVersion),
    i32 versionRemove(1: i32 id),
    bean.ClientVersion versionFindById(1: i32 id),
    list<bean.ClientVersion> versionSelectAll(),
    list<bean.ClientVersion> versionSelect(1: bean.ClientVersion clientVersion)
}
service CacheService{
    void bannerPut(1: string key,2:bean.BannerList banners)
    bean.BannerList bannerGet(1: string key)
}