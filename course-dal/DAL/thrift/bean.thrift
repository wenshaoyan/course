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
    7:i16 show_client_id
    8:i16 location

}
