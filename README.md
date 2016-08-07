# Wow_China

### 添加Post接口

* url：http://112.124.121.126:8080/addPost.action
* 请求方式：POST
* 请求参数：

```json
{
    "userId": 1,
    "token": "e1e04eb78b9f91bbd91fac9f0b86f868",
    "title": "Does anyone have time this weekend?",
    "company": "Dell",
    "website": "www.dell.com",
    "industryid": 1,
    "opento": "1,2",
    "headcount": 30,
    "status": 0,
    "cityid": 2,
    "address": "shanghai pudong chuangsha",
    "reward": "800",
    "description": "what the fuck"
}
```

* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": 8（返回postID）
}
```

### 查看Post消息的请求

* url: http://112.124.121.126:8080/getPostById.action?id=2
* 请求方式：GET
* 返回参数：

```json
{
    "code": 0,
    "message": "success",
    "result": {
        "user": {
            "id": 0,
            "username": null,
            "password": null,
            "token": null,
            "userimage": null,
            "realname": null,
            "university": null,
            "majorid": 0,
            "industryids": null,
            "skills": null,
            "location": null,
            "email": null,
            "tel": null,
            "certificates": null,
            "linkedinid": null,
            "linkedinusername": null,
            "linkedinprofileurl": null,
            "facebook": null
        },
        "post": {
            "id": 8,
            "title": "Does anyone have time this weekend?",
            "company": "Dell",
            "website": "www.dell.com",
            "industryid": 1,
            "opento": "1,2",
            "headcount": 30,
            "eligible": 0,
            "status": 0,
            "cityid": 2,
            "address": "shanghai pudong chuangsha",
            "reward": "800/Day",
            "description": "what the fuck",
            "updatetime": "2016-08-06 01:05",
            "userid": 1
        },
        "industry": {
            "id": 1,
            "industry": "Administration and office support"
        },
        "openToMajors": [
            {
                "id": 1,
                "major": "Business Administration"
            },
            {
                "id": 2,
                "major": "Impressionist Art"
            }
        ]
    }
}
```