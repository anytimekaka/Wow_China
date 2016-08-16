# Wow_China

### 消息种类

* Post发布者收到申请人申请的消息
* 申请人收到申请被拒绝 或者 被接受的消息

### 获取首页分页列表

* url:http://112.124.121.126:8080/postlist.action?industryId=2&pageSize=2&currentPage=1
* 请求参数说明：industryId：表示行业的分类ID，pageSize表示分页中每页包含几条数据，currentPage表示当前页号，从开始
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
    "description": "乐队鼓手"
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