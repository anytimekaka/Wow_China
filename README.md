# Wow_China

### 初始数据

账号：imshealth，密码：123456， token：d12cd741e009c71571503372d82d4cae
imshealth 账号下面添加了一些post，作为初始的测试数据
城市和行业信息已经按照Stan之前给的加到库里面了，专业信息我自己加了一些，暂且够用了！

### Response状态

```json
SUCCESS(0,"成功"),
ALREADY_APPLY(10, "已申请，不能重复申请"),
USER_IS_POSTER(11, "是发起者，不能申请");
```

### 消息

消息类型：

```json
UNREPLY(1, "未回复"),
REPLIED(2, "已回复"),
APPLIED(3, "已申请"),
ACCEPTED(4, "接受"),
REJECTED(5, "拒绝");
```

消息结构：

```json
        {
          "id": 7,          // 消息的ID
          "relatedId": 8,   // 关联的消息ID
          "userId": 1,      // 消息属于哪个用户
          "postId": 8,      // 消息关联哪个post
          "status": 2,      // 消息的状态（见上面说明）
          "updateTime": "2016-08-28 15:11:10"      // 更新时间
        }
```

说明：消息与消息之间是一对一关联的，一条post消息，对应一条apply消息。例如：用户在申请时，会生成一条apply消息（已申请），post发布者会收到一条post消息（未回复），这两条消息是一一对应的，使用relatedId作为关联

### 获取个人Post消息列表

* url: http://112.124.121.126:8080/getPostMessageList.action?userId=14&token=d12cd741e009c71571503372d82d4cae
* 请求参数说明：userId：用户ID，token：用户token
* 请求方式：GET
* 返回参数

```json
{
  "code": 0,
  "message": "获取Post消息列表成功",
  "result": [
    {
      "status": 1,
      "desc": "未回复",
      "messages": [
        {
          "id": 1,
          "relatedId": 2,
          "userId": 14,
          "postId": 17,
          "status": 1,
          "updateTime": "2016-08-28 21:08:53"
        }
      ]
    },
    {
      "status": 2,
      "desc": "已回复",
      "messages": []
    }
  ]
}
```

### 获取个人Apply消息列表

* url:http://112.124.121.126:8080/getApplyMessageList.action?userId=1&token=0a676539bdd470b4404eec6b115e0fae
* 请求参数说明：userId：用户ID，token：用户token
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "获取Apply消息列表成功",
  "result": [
    {
      "id": 2,
      "relatedId": 1,
      "userId": 1,
      "postId": 17,
      "status": 3,
      "updateTime": "2016-08-28 21:08:53"
    }
  ]
}
```

### 申请

* url:http://112.124.121.126:8080/apply.action?userId=4&token=c46ccba3a280dd18454a868b85e8c28d&postId=8
* 请求参数说明：userId：用户ID，token：用户token，postId：申请的post的ID
* 请求方式：GET
* 返回参数：

```json
{
  "code": 10,
  "message": "已申请，不能重复申请",
  "result": null
}
```

### 同意申请

* url:http://112.124.121.126:8080/accept.action?userId=1&token=e1e04eb78b9f91bbd91fac9f0b86f868&messageId=5&relatedId=6
* 请求参数说明：userId：用户ID，token：用户token，messageId：回复的哪条消息，relatedId：关联的那条apply消息ID
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "接受成功",
  "result": null
}
```

### 拒绝申请

* url:http://112.124.121.126:8080/refuse.action?userId=1&token=e1e04eb78b9f91bbd91fac9f0b86f868&messageId=7&relatedId=8
* 请求参数说明：userId：用户ID，token：用户token，messageId：回复的哪条消息，relatedId：关联的那条apply消息ID
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "拒绝成功",
  "result": null
}
```

### 查询用户所有的post接口

* url:http://112.124.121.126:8080/getPostListByUserId.action?userId=1
* 请求参数说明：userId:用户ID
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": [
    {
      "id": 1,
      "title": "title",
      "company": "anjuke",
      "website": "www.anjuke.com",
      "industryid": 0,
      "opento": null,
      "headcount": 0,
      "eligible": 0,
      "status": 0,
      "cityid": 0,
      "address": null,
      "reward": null,
      "description": null,
      "deadline": "2016-08-08",
      "userid": 1,
      "updatetime": null
    },
    {
      "id": 2,
      "title": "title",
      "company": "anjuke",
      "website": "www.anjuke.com",
      "industryid": 0,
      "opento": null,
      "headcount": 0,
      "eligible": 0,
      "status": 0,
      "cityid": 0,
      "address": null,
      "reward": null,
      "description": null,
      "deadline": "2016-08-08",
      "userid": 1,
      "updatetime": "2016-08-06 00:38:00"
    }
  ]
}
```

### 获取首页分页列表接口

* url:http://112.124.121.126:8080/postlist.action?industryId=2&pageSize=2&currentPage=1
* 请求参数说明：industryId：表示行业的分类ID，pageSize表示分页中每页包含几条数据，currentPage表示当前页号，从0开始
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": [
    {
      "id": 4,
      "title": "就差一个鼓手啦",
      "company": "Dell",
      "website": "www.dell.com",
      "address": "北京三里屯",
      "reward": "2000/小时",
      "description": "乐队鼓手",
      "userId": 3,
      "username": "anytimeka",
      "userimage": "1471088723670.jpg",
      "updatetime": "2016-08-13 13:58:03"
    },
    {
      "id": 5,
      "title": "就差一个鼓手了",
      "company": "Dell",
      "website": "www.dell.com",
      "address": "北京三里屯",
      "reward": "2000/小时",
      "description": "乐队鼓手",
      "userId": 3,
      "username": "anytimeka",
      "userimage": "1471088723670.jpg",
      "updatetime": "2016-08-13 13:57:53"
    }
  ]
}
```

### 搜索接口
* 按城市ID搜索：http://112.124.121.126:8080/searchByCityId.action?cityId=2&pageSize=5&currentPage=1
* 按面向专业的MajorID搜索：http://112.124.121.126:8080/searchByMajorId.action?majorId=1&pageSize=5&currentPage=1
* 按关键字搜索：http://112.124.121.126:8080/searchByKeyword.action?keyword=weekend&pageSize=5&currentPage=1
* 所有条件搜索：http://112.124.121.126:8080/search.action?cityId=2&majorId=0&keyword=&pageSize=5&currentPage=1
* 请求方式：GET
* 返回参数格式：与postlist接口一致

### 获取所有城市列表信息

* url:http://112.124.121.126:8080/queryCity.action
* 请求方式：GET
* 返回参数：

```json
{
    "code": 0,
    "message": "success",
    "result": [
        {
            "id": 1,
            "cityname": "shanghai"
        },
        {
            "id": 2,
            "cityname": "beijing"
        },
        {
            "id": 3,
            "cityname": "Hongkong"
        }
    ]
}
```

### 获取所有行业（Industry）列表信息

* url:http://112.124.121.126:8080/queryIndustry.action
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": [
    {
      "id": 1,
      "industry": "Administration and office support"
    },
    {
      "id": 2,
      "industry": "Advertising"
    },
    {
      "id": 3,
      "industry": "Arts"
    },
    {
      "id": 4,
      "industry": "Media"
    }
  ]
}
```

### 获取所有专业（Major）列表信息

* url:http://112.124.121.126:8080/queryMajor.action
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": [
    {
      "id": 1,
      "major": "Business Administration"
    },
    {
      "id": 2,
      "major": "Impressionist Art"
    },
    {
      "id": 3,
      "major": "music"
    }
  ]
}
```

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
    "cityid": 2,
    "address": "shanghai pudong chuangsha",
    "reward": "800",
    "description": "what the fuck",
    "deadline": "2016-08-08",
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

### 查看Post接口

* url: http://112.124.121.126:8080/getPostById.action?id=2
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": {
    "user": {
      "id": 3,
      "username": "anytimeka",
      "userimage": "1469373710923"
    },
    "post": {
      "id": 4,
      "title": "就差一个鼓手啦",
      "company": "Dell",
      "website": "www.dell.com",
      "industryid": 2,
      "opento": "2,3",
      "headcount": 10,
      "eligible": 0,
      "status": 0,
      "cityid": 2,
      "address": "北京三里屯",
      "reward": "2000/小时",
      "description": "乐队鼓手",
      "deadline": "2016-08-08",
      "userid": 3,
      "updatetime": "2016-08-13 13:58:03"
    },
    "industry": {
      "id": 2,
      "industry": "Advertising"
    },
    "openToMajors": [
      {
        "id": 2,
        "major": "Impressionist Art"
      },
      {
        "id": 3,
        "major": "music"
      }
    ]
  }
}
```

### 更新Post接口

* url: http://112.124.121.126:8080/updatePost.action
* 请求方式：Post
* 请求参数：

```json
{
    "userId": 3,
    "token": "0a676539bdd470b4404eec6b115e0fae",
    "id":"4",
    "title": "就差一个鼓手啦",
    "company": "Dell",
    "website": "www.dell.com",
    "opento": "2,3",
    "headcount": 10,
    "cityid": 2,
    "address": "北京三里屯",
    "reward": "2000/小时",
    "description": "乐队鼓手",
    "deadline": "2016-10-10"
}
```

* 返回结果：

```json
{
  "code": 0,
  "message": "success",
  "result": null
}
```



### 注册接口

* url: http://112.124.121.126:8080/register.action?username=tiny&password=123456
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": {
    "userId": 9,
    "token": "f0d36d4787060cfd4a3c048dd7f82d43"
  }
}
```

### 登录接口

* url: http://112.124.121.126:8080/login.action?username=tiny&password=123456
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": {
    "userId": 9,
    "token": "f0d36d4787060cfd4a3c048dd7f82d43"
  }
}
```

### 查看用户信息接口

* url: http://112.124.121.126:8080/viewUserInfo.action?userId=3
* 请求方式：GET
* 返回参数：

```json
{
  "code": 0,
  "message": "success",
  "result": {
    "id": 3,
    "userimage": "1469373710923",
    "realname": "王贵生",
    "birthday": "2001-08-01",
    "sex": 1,
    "experience": "no experience",
    "university": "TJ",
    "major": {
      "id": 1,
      "major": "Business Administration"
    },
    "industrys": [
      {
        "id": 1,
        "industry": "Administration and office support"
      },
      {
        "id": 2,
        "industry": "Advertising"
      },
      {
        "id": 3,
        "industry": "Arts"
      }
    ],
    "skills": null,
    "location": null,
    "email": null,
    "tel": null,
    "certificates": null,
    "linkedinid": null,
    "linkedinusername": null,
    "linkedinprofileurl": null,
    "facebook": null
  }
}
```

### 编辑用户信息接口

* url: http://112.124.121.126:8080/editUserInfo.action
* 请求方式：Post
* 请求参数：

```json
{
    "userId":3,
    "token":"0a676539bdd470b4404eec6b115e0fae",

    "realname": "王贵生",
    "birthday": "2001-08-01",
    "sex": 1,
    "experience": "no experience",
    "university": "TJ",
    "majorid": 1,
    "industryids": "2,3",
    "skills": null,
    "location": "上海",
    "email": "anytimekaka@163.com",
    "tel": "13900000000",
    "certificates": null,
    "linkedinid": "anytimekaka",
    "linkedinusername": null,
    "linkedinprofileurl": null,
    "facebook": null
}
```

* 返回参数：

```json
{
  "code": 0,
  "message": "update userInfo success",
  "result": null
}
```

### 上传图像图片接口

* url: http://112.124.121.126:8080/uploadUserImage.action
* 请求方式：Post
* 请求参数：file：图片文件；userId：用户ID；token：用户token
* 用户头像图片说明：图片名保存在用户信息的userimage字段，如：29e2a35.jpg 图片资源url：http://112.124.121.126:8080/userimage/29e2a35.jpg
* 返回参数：

```json
{
  "code": 0,
  "message": "update userInfo success",
  "result": null
}
```