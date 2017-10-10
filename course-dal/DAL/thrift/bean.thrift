namespace java com.wenshao.dal.thriftgen
struct User{
    1: i16 id,
    2: string name,
    3: string head,
    4: string tel,
    5: string device_uuid,
    6: string register_time,
    7: string create_time,
    8: string update_time,
    9: string password
}
struct Banner{
    1:i32 id
    2:string create_time
    3:string update_time
    4:i32 user_id
    5:string image_url
    6:string redirect_url
    7:i32 show_client_id
    8:i32 location
}
struct ClientVersion{
    1:i32 id
    2:string create_time
    3:string update_time
    4:string version_name
    5:string version_number
    6:i32 client
    7:string download_url

}
struct ClientSide{
    1:i32 id
    2:string create_time
    3:string update_time
    4:string name
    5:string package_name
}
struct BannerList{
    1:list<Banner> data
}