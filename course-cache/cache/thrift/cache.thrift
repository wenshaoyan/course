namespace java com.wenshao.cache.thriftgen

include "bean.thrift"

service CacheService{
    void bannerPut(1: string key,2:bean.BannerList banners)
    bean.BannerList bannerGet(1: string key)
}