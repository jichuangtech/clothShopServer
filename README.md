# clothShopServer
- ### 金凤布料商城

  - 服务器地址:www.jichuangtech.site/clothshopserver


-  ``RESTFUL``接口规范资料
    - http://www.ruanyifeng.com/blog/2014/05/restful_api.html
    - http://www.ruanyifeng.com/blog/2011/09/restful.html


- ### 接口

  - ##### 商品

    | 作用域                          | 接口说明           | 参数说明               |
    | ---------------------------- | -------------- | ------------------ |
    | /api/goods                   | 获取全部商品         | 无                  |
    | /api/goods/{goodsId}         | 获取某个商品         | ``{goodId}``商品Id   |
    | /api/goods/picture/{picName} | 查看商品的图片/详细信息图片 | ``{picName}``图片的名字 |
    | /api/goods/hot               | 获取热销商品         | 无                  |
    | /api/goods/hot/{goodsId}     | 获取某个热销商品       | ``{goodId}``商品Id   |

  - ##### 商品分类

    | 作用域                                      | 接口说明          | 参数说明                        |
    | ---------------------------------------- | ------------- | --------------------------- |
    | /api/goodsCategories                     | 获取全部商品分类      | 无                           |
    | /api/goodsCategories/{goodsCategoryId}   | 获取某个商品分类      | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/{goodsCategoryId}/goods | 获取某个商品分类的全部商品 | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/picture/{picName}   | 查看商品分类的图片     | ``{picName}``图片的名字          |

  - ##### 订单

    | 作用域                                      | 接口说明   | 参数说明                                     |
    | ---------------------------------------- | ------ | ---------------------------------------- |
    | /clothshopserver/api/order/{userId}/{orderStatus} | 查询用户订单 | {userId}:用户Id;{orderStatus}:订单状态，其中0表所有订单 |
    | /clothshopserver/api/order/{userId}      | 生成订单   | {userId}:用户Id                            |

  - ##### 收货地址

    | 作用域                                      | 接口说明   | 参数说明                                     |
    | ---------------------------------------- | ------ | ---------------------------------------- |
    | /clothshopserver/api/address/{userId}/{orderStatus} | 查询用户订单 | {userId}:用户Id;{orderStatus}:订单状态，其中0表所有订单 |
    | /clothshopserver/api/order/{userId}      | 生成订单   | {userId}:用户Id                            |

  -   ##### 规格

      | 作用域                                      | 接口说明   | 参数说明                  |
      | ---------------------------------------- | ------ | --------------------- |
      | /clothshopserver/api/goodsSpec           | 查询所有规格 | 无                     |
      | /clothshopserver/api/goodsSpec/{goodsSpecId} | 查询某个规格 | ``{goodsSpecId}``规格Id |
      |                                          |        |                       |

  -   ##### 购物车

      | 作用域                     | 接口说明                | 参数说明                                |
      | ----------------------- | ------------------- | ----------------------------------- |
      | /api/goodsCart/{userId} | ``GET``，查询某用户的所有购物车 | ``{userId}``用户Id                    |
      | /api/goodsCart/{userId} | ``PUT``，某用户生成购物车    | ``{userId}``用户Id，请求体``GoodsCartVO`` |
      | /api/goodsCart/{cartId} | ``DELETE``，删除某购物车   | `{cartId}`要删除的购物车的Id                |

      PS:  生成购物车的接口``PUT  /api/goodsCart/{userId}``，需要携带下面格式的``请求体``

      ```json
      {
          "goodsVO":
              {
                  "goodsId":1,           //商品id
                  "goodsPrice":12.00,    //商品价格
                  "goodsNum":99,         //商品价格
                  "goodsName":"美国网1",  //商品名字
                  "specName":"码",        //规格
                  "goodsSn":"13233232"    //商品货号
              }

      }
      ```

      ​