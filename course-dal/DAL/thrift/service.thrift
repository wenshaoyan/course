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
    i32 remove(1: bean.Banner banner),
    bean.Banner findById(1: i32 id),
    list<bean.Banner> selectAll(),
    list<bean.Banner> select(1: bean.Banner banner),
    list<bean.Banner> selectQuery(1: bean.Banner banner,2: bean.Query query),
    list<bean.Banner> selectCustom(1: bean.Banner banner,2:bean.Custom custom),
    list<bean.Banner> selectQueryCustom(1: bean.Banner banner,2: bean.Query query,3:bean.Custom custom),
    list<bean.Banner> selectQueryCustomNotCache(1: bean.Banner banner,2: bean.Query query,3:bean.Custom custom),
    list<bean.Banner> selectQueryNoCache(1: bean.Banner banner,2: bean.Query query)

}
service ClientService{
    i32 clientInsert(1: bean.ClientSide clientSide),
    i32 clientUpdate(1: bean.ClientSide clientSide),
    i32 clientRemove(1: i32 id),
    bean.ClientSide clientFindById(1: i32 id),
    list<bean.ClientSide> clientSelectAll(),
    list<bean.ClientSide> clientSelect(1: bean.ClientSide clientSide),
    list<bean.ClientSide> clientSelectQuery(1: bean.ClientSide clientSide,2: bean.Query query),
    list<bean.ClientSide> clientSelectCustom(1: bean.ClientSide clientSide,2:bean.Custom custom),
    list<bean.ClientSide> clientSelectQueryCustom(1: bean.ClientSide clientSide,2: bean.Query query,3:bean.Custom custom)


    i32 versionInsert(1: bean.Version version),
    i32 versionUpdate(1: bean.Version version),
    i32 versionRemove(1: i32 id),
    bean.Version versionFindById(1: i32 id),
    list<bean.Version> versionSelectAll(),
    list<bean.Version> versionSelect(1: bean.Version version),
    list<bean.Version> versionSelectQuery(1: bean.Version version,2: bean.Query query),
    i32 versionCountSelectQuery(1: bean.Version version,2: bean.Query query)

}
service CacheService{
    void bannerPut(1: string key,2:bean.BannerList banners)
    bean.BannerList bannerGet(1: string key)
}
service CourseService{
    i32 courseInsert(1: bean.Course course) throws (1: bean.RequestException qe),
    i32 courseUpdate(1: bean.Course course) throws (1: bean.RequestException qe),
    i32 courseRemove(1: bean.Course course) throws (1: bean.RequestException qe),
    bean.Course courseFindById(1: i32 id) throws (1: bean.RequestException qe),
    list<bean.Course> courseSelect(1: bean.Course course),
    list<bean.Course> courseSelectQuery(1: bean.Course course,2: bean.Query query),
    list<bean.Course> courseSelectCustom(1: bean.Course course,2:bean.Custom custom),
    list<bean.Course> courseSelectQueryCustom(1: bean.Course course,2: bean.Query query,3:bean.Custom custom),
    list<bean.Course> courseSelectQueryCustomNotCache(1: bean.Course course,2: bean.Query query,3:bean.Custom custom),
    list<bean.Course> courseSelectQueryNoCache(1: bean.Course course,2: bean.Query query),
    i32 courseCountSelectQuery(1: bean.Course course,2: bean.Query query),

    string videoInsert(1: bean.Video video),
    i32 videoUpdate(1: bean.Video video),
    i32 videoRemove(1: bean.Video video),
    bean.Video versionFindById(1: string id),
    list<bean.Video> videoSelectAll(),
    list<bean.Video> videoSelect(1: bean.Video video)
}
# 公共服务
service CommonService{
    i32 topicOptionInsert(1: bean.TopicOption topicOption) throws (1: bean.RequestException re),
    i32 topicOptionUpdate(1: bean.TopicOption topicOption) throws (1: bean.RequestException re),
    i32 topicOptionRemove(1: bean.TopicOption topicOption) throws (1: bean.RequestException re),
    list<bean.TopicOption> topicOptionSelect(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    list<bean.TopicOption> topicOptionSelectNoCache(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    i32 topicOptionCount(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),

    i32 topicInsert(1: bean.Topic topic) throws (1: bean.RequestException re),
    i32 topicUpdate(1: bean.Topic topic) throws (1: bean.RequestException re),
    i32 topicRemove(1: bean.Topic topic) throws (1: bean.RequestException re),
    list<bean.Topic> topicSelect(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    list<bean.Topic> topicSelectNoCache(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    i32 topicCount(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),

    i32 topicBankInsert(1: bean.TopicBank topicBank) throws (1: bean.RequestException re),
    i32 topicBankUpdate(1: bean.TopicBank topicBank) throws (1: bean.RequestException re),
    i32 topicBankRemove(1: bean.TopicBank topicBank) throws (1: bean.RequestException re),
    list<bean.TopicBank> topicBankSelect(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    list<bean.TopicBank> topicBankSelectNoCache(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),
    i32 topicBankCount(1: bean.AbstractSql abstractSql) throws (1: bean.RequestException re),

}
