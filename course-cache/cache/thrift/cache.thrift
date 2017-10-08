namespace java com.wenshao.cache.thriftgen

include "bean.thrift"

service CacheService{
    void bannerPut(1: string key,2:list<bean.Banner> banners)
    list<bean.Banner> bannerGet(1: string key)


}