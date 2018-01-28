namespace java com.wenshao.dal.thriftgen
exception RequestException{
    1: i32 code,
    2: string message,
    3: string serverName,
    4: string methodName,
    5: string fullMessage
}
struct User{
    1: i32 id,
    2: string name,
    3: string head,
    4: string tel,
    5: string device_uuid,
    6: string register_time,
    7: string create_time,
    8: string update_time,
    9: string password,
    10: i32 role_id
}
struct Role{
    1: i32 id,
    2: string name,
    3: string create_time,
    4: string update_time,
    5: string  permission
}
struct Banner{
    1:i32 id
    2:string create_time
    3:string update_time
    4:i32 user_id
    5:string image_url
    6:string redirect_url
    7:i32 client_id
    8:i32 location
}
struct Version{
    1:i32 id
    2:string create_time
    3:string update_time
    4:string version_name
    5:i32 version_number
    6:i32 client_id
    7:string download_url
    8:string description

}
struct ClientSide{
    1:i32 id
    2:string create_time
    3:string update_time
    4:string name
    5:string package_name
    6:list<Version> versions
}
struct BannerList{
    1:list<Banner> data
}
struct CourseType{
    1:i32 id
    2:string name
}
struct CourseTypeGroup{
    1:i32 id
    2:string name
    3:list<CourseType> courseTypes
}
struct CourseTypeAll{
    1:i32 id
    2:string name
    3:i32 group_id
    4:string group_name
}
struct Course{
    1:i32 id
    2:string title
    3:string create_time
    4:string update_time
    5:double price
    6:string describe
    7:i32 status
    8:i32 author_id
    9:CourseTypeAll courseTypeAll
    10:string image_url
}
struct Video{
    1:string id
    2:string title
    3:string resource_path
    4:i32 resource_time
    5:i32 location
    6:i32 course_id
    7:i32 status
    8:i32 play_number
    9:string create_time
    10:string update_time
}
struct Query{
    1:string create_time_start,
    2:string create_time_end,
    3:string update_time_start,
    4:string update_time_end,
    5:i32 page,
    6:i32 limit,
    7:string sort_by,
    8:string order
}
struct Custom{
    1:set<string> tables
}
struct Where{
    1:string type
    2:string eq
    3:string ne
    4:string gt
    5:string gte
    6:string lt
    7:string lte
    8:list<string> between
    9:list<string> notBetween
    10:list<string> any
    11:list<string> notAny
    12:string like
    13:string notLike
}
struct Include{
    1: string association
    2: bool isRequired
    3: Where where
    4: list<Include> includes
}
struct AbstractSql{
    1:list<string> selects
    2:map<string,Where> where
    3:string order
    4:string group
    5:list<i32> limit
    6:list<Include> includes
    7:string mode
}
struct TopicOption{
    1:i32 to_id
    2:string to_context
    3:i32 to_topic_id
    4:string to_create_time
    5:string to_update_time

}
struct Topic{
    1:i32 topic_id
    2:string topic_title
    3:string topic_type
    4:string topic_analysis
    5:string topic_correct_answer
    6:double topic_score
    7:string topic_create_time
    8:string topic_update_time
    9:list<TopicOption> topicOptions
}
struct TopicBank{
    1:i32 tb_id
    2:string tb_name
    3:string tb_type
    4:string tb_create_time
    5:string tb_update_time
    6:list<Topic> topics
}
