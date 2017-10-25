namespace java com.wenshao.dal.thriftgen

include "bean.thrift"

service UserService{
    i32 userInsert(1: bean.User user),
    bean.User userFindById(1: i32 id),
    list<bean.User> userSelectAll(),
    list<bean.User> userSelect(1: bean.User user)
    list<bean.User> userQuery(1: bean.Query query)
    list<bean.User> userSelectQuery(1: bean.User user,2: bean.Query query)
    i32 userCountSelectQuery(1: bean.User user,2: bean.Query query)

    i32 roleInsert(1: bean.Role role),
    bean.Role roleFindById(1: i32 id),
    list<bean.Role> roleSelectAll(),
    list<bean.Role> roleSelect(1: bean.Role role)
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
    list<bean.ClientSide> clientSelectQuery(1: bean.ClientSide clientSide,2: bean.Query query),

    i32 versionInsert(1: bean.Version version),
    i32 versionUpdate(1: bean.Version version),
    i32 versionRemove(1: i32 id),
    bean.Version versionFindById(1: i32 id),
    list<bean.Version> versionSelectAll(),
    list<bean.Version> versionSelect(1: bean.Version version),
    list<bean.Version> versionSelectQuery(1: bean.Version version,2: bean.Query query),




}
service CacheService{
    void bannerPut(1: string key,2:bean.BannerList banners)
    bean.BannerList bannerGet(1: string key)
}
service CourseService{
    i32 courseInsert(1: bean.Course course),
    i32 courseUpdate(1: bean.Course course),
    i32 courseRemove(1: i32 id),
    bean.Course courseFindById(1: i32 id),
    list<bean.Course> courseSelectAll(),
    list<bean.Course> courseSelect(1: bean.Course course),

    string videoInsert(1: bean.Video video),
    i32 videoUpdate(1: bean.Video video),
    i32 videoRemove(1: string id),
    bean.Video versionFindById(1: string id),
    list<bean.Video> videoSelectAll(),
    list<bean.Video> videoSelect(1: bean.Video video)
}
