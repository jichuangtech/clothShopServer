# clothShopServer
- ### 金凤布料商城

  - 服务器地址:www.jichuangtech.site/clothshopserver


-  ``RESTFUL``接口规范资料
    - http://www.ruanyifeng.com/blog/2014/05/restful_api.html
    - http://www.ruanyifeng.com/blog/2011/09/restful.html


- ### 接口

  - ##### 商品

    | 作用域                  | 接口说明   | 参数说明             |
    | -------------------- | ------ | ---------------- |
    | /api/goods           | 获取全部商品 | 无                |
    | /api/goods/{goodsId} | 获取某个商品 | ``{goodId}``商品Id |

  - ##### 商品分类

    | 作用域                                      | 接口说明          | 参数说明                        |
    | ---------------------------------------- | ------------- | --------------------------- |
    | /api/goodsCategories                     | 获取全部商品分类      | 无                           |
    | /api/goodsCategories/{goodsCategoryId}   | 获取某个商品分类      | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/{goodsCategoryId}/goods | 获取某个商品分类的全部商品 | ``{goodsCategoryId}``商品分类Id |
    | /api/goodsCategories/picture/{picName}   | 查看商品分类的图片     | ``{picName}``图片的名字          |

  - ##### 订单

  | 作用域  | 接口说明 | 参数说明 |
  | ---- | ---- | ---- |
  |      |      |      |
  |      |      |      |
  |      |      |      |

  ​

  ##### 