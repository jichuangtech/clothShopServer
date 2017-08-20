# clothShopServer
- ### 金凤针织商城服务端

  - 服务器地址:www.jichuangtech.site/clothshopserver


-  ``RESTFUL``接口规范资料
    - http://www.ruanyifeng.com/blog/2014/05/restful_api.html
    - http://www.ruanyifeng.com/blog/2011/09/restful.html


- ### 接口 

  ```
  服务端已添加 swagger自动生成RESTFUI文档工具，查看API更方便。
  访问地址：http://www.jichuangtech.site/clothshopserver/swagger-ui.html#/
  ```

  ​

  - ##### 商品

    | 作用域                          | 接口说明                | 参数说明               |
    | ---------------------------- | ------------------- | ------------------ |
    | /api/goods                   | 获取全部商品              | 无                  |
    | /api/goods/{goodsId}         | 获取某个商品              | ``{goodId}``商品Id   |
    | /api/goods/picture/{picName} | 查看商品的图片/详细信息图片      | ``{picName}``图片的名字 |
    | /api/goods/hot               | 获取热销商品              | 无                  |
    | /api/goods/recommend         | 获取推荐商品，可以用于显示banner | 无                  |
    | /api/goods/hot/{goodsId}     | 获取某个热销商品            | ``{goodId}``商品Id   |

  - ##### 商品分类

    | 作用域                                      | 接口说明          | 参数说明                        |
    | ---------------------------------------- | ------------- | --------------------------- |
    | /api/goodsCategories                     | 获取全部商品分类      | 无                           |
    | /api/goodsCategories/{goodsCategoryId}   | 获取某个商品分类      | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/{goodsCategoryId}/goods | 获取某个商品分类的全部商品 | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/picture/{picName}   | 查看商品分类的图片     | ``{picName}``图片的名字          |

  - ##### 订单

    | 作用域                                      | 接口说明            | 参数说明                                     |
    | ---------------------------------------- | --------------- | ---------------------------------------- |
    | /clothshopserver/api/order/{userId}/{orderStatus} | ``GET``, 查询用户订单 | {userId}:用户Id;{orderStatus}:订单状态，其中0表所有订单 |
    | /clothshopserver/api/order/{userId}      | ``POST``,生成订单   | {userId}:用户Id                            |
    注：
    ``/clothshopserver/api/order/{userId}/{orderStatus}``
    ``返回体``
    {"statusCode":200,"msg":null,"data":[{"totalAmount":0.00,"address":"福建省厦门市软件园二期","mobile":"1885054123456","consignee":"zxx","orderSn":"20170726","orderId":1,"userId":16777215,"orderStatus":1,"goodsVO":[{"goodsId":1,"shopPrice":0.00,"goodsNum":1,"goodsName":"锦纶加密美国网","specName":"千克","color":" 西瓜红","goodsSn":"","originalImg":""},{"goodsId":2,"shopPrice":0.00,"goodsNum":2,"goodsName":"锦纶闪光美国网","specName":"千克","color":"西瓜红","goodsSn":"","originalImg":""}]}]}
    ``/clothshopserver/api/order/{userId}``
    ``请求体``
    {"addressId":7,"goodsReqVOList":[{"goodsId":2,"goodsNum":1,"specId":1,"colorId":1},{"goodsId":1,"goodsNum":2,"specId":1,"colorId":1}]}                            

  - ##### 收货地址

    | 作用域                                      | 接口说明              | 参数说明                                     |
    | ---------------------------------------- | ----------------- | ---------------------------------------- |
    | /clothshopserver/api/useraddress/{userId} | ``GET``, 查询用户收货地址 | {userId}:用户Id                            |
    | /clothshopserver/api/useraddress/region/{parentId} | ``GET``, 查询地区信息   | {parentId}:上级地区Id，其中0表示查询国家地区信息,86为查询所有省份信息 |
    | /clothshopserver/api/useraddress/address | ``POST``, 新增收货地址  | 请求体看备注                                   |
    | /clothshopserver/api/useraddress/defaultaddress | ``POST``, 修改默认地址  | 请求参数：oldAddressId:旧的默认地址id;newAddressId:新的默认地址id |
    注：
    ``/clothshopserver/api/useraddress/{userId}``
    ``返回体``
    {"statusCode":200,"msg":null,"data":[{"addressId":1,"userId":16777215,"consignee":"张晓雪","address":"福建省厦门市思明区软件园二期","zipcode":"361000","mobile":"18850541234","isDefault":0},{"addressId":2,"userId":16777215,"consignee":"刘雪花","address":"福建省厦门市湖里区SM城市广场","zipcode":"3610001","mobile":"18850123456","isDefault":1}]}
    ``/clothshopserver/api/useraddress/region/{parentId}``
    ``返回体``
    {"statusCode":200,"msg":null,"data":[{"id":130000,"name":"河北省","level":1,"parentId":86},{"id":140000,"name":"山西省","level":1,"parentId":86},{"id":150000,"name":"内蒙古自治区","level":1,"parentId":86},{"id":210000,"name":"辽宁省","level":1,"parentId":86},{"id":220000,"name":"吉林省","level":1,"parentId":86},{"id":230000,"name":"黑龙江省","level":1,"parentId":86},{"id":320000,"name":"江苏省","level":1,"parentId":86},{"id":330000,"name":"浙江省","level":1,"parentId":86},{"id":340000,"name":"安徽省","level":1,"parentId":86},{"id":350000,"name":"福建省","level":1,"parentId":86},{"id":360000,"name":"江西省","level":1,"parentId":86},{"id":370000,"name":"山东省","level":1,"parentId":86},{"id":410000,"name":"河南省","level":1,"parentId":86},{"id":420000,"name":"湖北省","level":1,"parentId":86},{"id":430000,"name":"湖南省","level":1,"parentId":86},{"id":440000,"name":"广东省","level":1,"parentId":86},{"id":450000,"name":"广西壮族自治区","level":1,"parentId":86},{"id":460000,"name":"海南省","level":1,"parentId":86},{"id":510000,"name":"四川省","level":1,"parentId":86},{"id":520000,"name":"贵州省","level":1,"parentId":86},{"id":530000,"name":"云南省","level":1,"parentId":86},{"id":540000,"name":"西藏自治区","level":1,"parentId":86},{"id":610000,"name":"陕西省","level":1,"parentId":86},{"id":620000,"name":"甘肃省","level":1,"parentId":86},{"id":630000,"name":"青海省","level":1,"parentId":86},{"id":640000,"name":"宁夏回族自治区","level":1,"parentId":86},{"id":650000,"name":"新疆维吾尔自治区","level":1,"parentId":86},{"id":710000,"name":"台湾省","level":1,"parentId":86},{"id":810000,"name":"香港特别行政区","level":1,"parentId":86},{"id":820000,"name":"澳门特别行政区","level":1,"parentId":86}]}
    ``/clothshopserver/api/useraddress/address``
    ``请求体``
    {"userId":16777215,"consignee":"张晓雪","province":350000,"city":350200,"district":350203,"address":"瑞景商业广场","zipcode":"361000","mobile":"18850541234","isDefault":1}

  - ##### 规格

      | 作用域                                      | 接口说明   | 参数说明                  |
      | ---------------------------------------- | ------ | --------------------- |
      | /clothshopserver/api/goodsSpec           | 查询所有规格 | 无                     |
      | /clothshopserver/api/goodsSpec/{goodsSpecId} | 查询某个规格 | ``{goodsSpecId}``规格Id |
      |                                          |        |                       |

  - ##### 购物车

      | 作用域                        | 接口说明                | 参数说明                      |
      | -------------------------- | ------------------- | ------------------------- |
      | /api/goodsCart/{userId}    | ``GET``，查询某用户的所有购物车 | ``{userId}``用户Id          |
      | /api/goodsCart             | ``PUT``，某用户生成购物车    | 请求体``GoodsCartVO``        |
      | /api/goodsCart/{cartId}    | ``DELETE``，删除某购物车   | `{cartId}`要删除的购物车的Id      |
      | /api/goodsCart/goodsNumber | ``POST``，修改购物车商品数量  | 请求体``AlterGoodsNumberVO`` |

      PS:  生成购物车的接口``PUT  /api/goodsCart``，需要携带下面格式的 

      ``请求体``

      ```json
      {
          "userId":12,
          "goodsId":3,
          "colorId":3,
          "goodsNum":100,
          "specId":2
      }
      ```
      ``POST  /api/goodsCart/goodsNumber``

      ```son
      {
          "goodsCartId":6,     //购物车ID
          "goodsNum":12111     //修改后，购物车商品的数量
      }
      ```

      ​

  - 返回体


  ```json
  { 
    "statusCode": -1,     //除了200成功，其他用于表示错误
    "msg": null,          //对应 statusCode的描述信息，目前嗨未来进行赋值，都是null
    "data": null 		    //响应给用户的数据，后期所有接口，都采用这种结构返回
  }
  ```

- ###   微信小程序中用户登录和登录态维护

  ```
  1.小程序 wx.login()将返回数据code，通过wx.request()发送给服务端；
  2.服务端用这个code等相关信息去微信服务器请求session_key 和 openid；
  3.根据 2步骤请求到的数据 openid session_key unionid  ， 生成一个唯一字符串sessionid作为键，将openid和session_key作为值，存入redis，超时时间设置为2小时；
  4.将 sessionid 返回给 小程序客户端；
  5.小程序每次调用那些需要登录后才有权限的访问的后台服务时，你可以将保存在storage中的sessionid取出并携带在请求中；

  相关链接：
  （1）https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject；
  （2）http://www.jianshu.com/p/c5f6c98b2685
  ```

  ​