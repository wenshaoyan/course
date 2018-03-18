# mail-center  邮件收集器
## 简介
```text
topic:basis-mail
groupId:default
messgae.key:uuid
message.value.to: 发送地址,多个以;(分号分隔)
message.value.subject: 邮件主题
message.value.body: 邮件内容
message.value.body_type: 邮件内容的格式 html|text
message.value.level: 邮件的提示基本, info ware error fatal
```
## 依赖
go get github.com/Shopify/sarama
go get github.com/bsm/sarama-cluster

## 环境变量
GO_ENV:develop|test|release|production
KAFKA_URL:ip:port
SEND_MAIL_USER:发送邮件的用户
SEND_MAIL_PASSWORD:发送邮件的密码

