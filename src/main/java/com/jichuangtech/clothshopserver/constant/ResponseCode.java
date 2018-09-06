package com.jichuangtech.clothshopserver.constant;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bingo on 2017/8/27.
 */
public class ResponseCode {

    public static final int ACCESS_TOKEN_NOT_FOUND = 101;
    public static final int TOKEN_INVALID = 102;
    public static final int CODE_SUCCESS = 200;

    // TODO: 2018/2/2 要修改下状态吗
    //商品系列返回码
    public static final int CODE_GOODS_x = 100;
    public static final int CODE_GOODS_ADD_ERROR = 101;
    public static final int CODE_GOODS_NOT_FOUND = 102;
    public static final int CODE_GOODS_HOT_ERROR = 103;
    public static final int CODE_GOODS_RECOMMEND_ERROR = 104;
    public static final int CODE_GOODS_GET_ALL_ERROR = 105;
    public static final int CODE_GOODS_DELETE_NOT_FOUND = 106;

    //商品分类系列返回码
    public static final int CODE_GOODS_CATEGORY_D = 300;
    public static final int CODE_GOODS_CATEGORY_GET_ERROR = 301;
    public static final int CODE_GOODS_CATEGORY_GET_GOODS_ERROR = 302;
    public static final int CODE_GOODS_CATEGORY_SAVE_PIC_ERROR = 303;
    public static final int CODE_CATEGORY_DELETE_NOT_FOUND = 304;

    //购物车分类系列返回码
    public static final int CODE_GOODS_CART_x = 400;
    public static final int CODE_GOODS_CART_GET_ERROR = 401;
    public static final int CODE_GOODS_CART_ADD_ERROR = 402;
    public static final int CODE_GOODS_CART_DELETE_NOT_FOUND_ERROR = 403;

    //订单系列返回码
    public static final int CODE_ORDER_x = 500;
    public static final int CODE_ORDER_NOT_FOUND = 501;
    public static final int CODE_ORDER_GET_ERROR = 502;
    public static final int CODE_ORDER_DETAIL_GET_ERROR = 503;
    public static final int CODE_ORDER_CREATE_ERROR = 504;

    //收货地址系列返回码
    public static final int CODE_ADDRESS_x = 600;
    public static final int CODE_ADDRESS_DETAIL_GET_ERROR = 601;
    public static final int CODE_ADDRESS_ADD_ERROR = 602;
    public static final int CODE_ADDRESS_DEFAULT_GET_ERROR = 603;
    public static final int CODE_ADDRESS_REGION_GET_ERROR = 604;
    public static final int CODE_ADDRESS_ALL_GET_ERROR = 605;

    //用户系列返回码
    public static final int CODE_USER_LIST_ERROR = 700;

    //登录系列返回码
    public static final int CODE_LOGIN_CMS_ERROR = 800;

    //支付系列返回码
    public static final int CODE_PREF_PAY_ERROR = 900;

    //图片
    public static final int CODE_PIC_SAVE_ERROR = 1000;
    public static final int CODE_PIC_DELETE_ERROR = 1001;
    public static final int CODE_PIC_GET_ERROR = 1002;

    //用户管理返回码
    public static final int CODE_USER_NOT_FOUND = 1100;
    public static final int CODE_PASSWORD_WRONG = 1101;
    public static final int CODE_USER_ALREADY_EXIST = 1102;
    public static final int CODE_LOGOUT_ERROR_HAS_NOT_LOGIN = 1103;

    public static final Map<Integer, String> sCodeMsgMap = new HashMap();

    static {
        sCodeMsgMap.put(CODE_SUCCESS, " success... ");
        sCodeMsgMap.put(CODE_CATEGORY_DELETE_NOT_FOUND, "goods cart not found ");
        sCodeMsgMap.put(CODE_GOODS_NOT_FOUND, "goods not found");
        sCodeMsgMap.put(CODE_GOODS_HOT_ERROR, "get hot goods error");
        sCodeMsgMap.put(CODE_GOODS_RECOMMEND_ERROR, "get recommended goods error");
        sCodeMsgMap.put(CODE_GOODS_GET_ALL_ERROR, "get all goods error");
        sCodeMsgMap.put(CODE_GOODS_CATEGORY_GET_ERROR, "get categories error");
        sCodeMsgMap.put(CODE_GOODS_CATEGORY_GET_GOODS_ERROR, "get categories'goods error");
        sCodeMsgMap.put(CODE_GOODS_CART_GET_ERROR, "get goods cart error");
        sCodeMsgMap.put(CODE_GOODS_CART_ADD_ERROR, "add goods cart error");
        sCodeMsgMap.put(CODE_ORDER_GET_ERROR, "get order error");
        sCodeMsgMap.put(CODE_ORDER_DETAIL_GET_ERROR, "get order detail error");
        sCodeMsgMap.put(CODE_ADDRESS_DETAIL_GET_ERROR, "get address detail error");
        sCodeMsgMap.put(CODE_ADDRESS_ADD_ERROR, "add address error");
        sCodeMsgMap.put(CODE_ADDRESS_DEFAULT_GET_ERROR, "get default address error");
        sCodeMsgMap.put(CODE_ADDRESS_REGION_GET_ERROR, "get address region error");
        sCodeMsgMap.put(CODE_ADDRESS_ALL_GET_ERROR, "get user all address error");
        sCodeMsgMap.put(CODE_USER_LIST_ERROR, "get all user error");
        sCodeMsgMap.put(CODE_LOGIN_CMS_ERROR, "cms login error");
        sCodeMsgMap.put(CODE_PREF_PAY_ERROR, "pref pay error");
        sCodeMsgMap.put(CODE_ORDER_CREATE_ERROR, "create order error");
        sCodeMsgMap.put(CODE_GOODS_CART_DELETE_NOT_FOUND_ERROR, "delete goodsCart error : cart not found..");
        sCodeMsgMap.put(CODE_GOODS_CATEGORY_SAVE_PIC_ERROR, "save category pic error... ");
        sCodeMsgMap.put(CODE_PIC_SAVE_ERROR, " save picture error...");
        sCodeMsgMap.put(CODE_PIC_DELETE_ERROR, " delete picture error...");
        sCodeMsgMap.put(CODE_PIC_GET_ERROR, " geta picture error...");
        sCodeMsgMap.put(CODE_GOODS_DELETE_NOT_FOUND, " delete goods error : goods not found..");
        sCodeMsgMap.put(CODE_USER_NOT_FOUND, " user not found...");
        sCodeMsgMap.put(CODE_PASSWORD_WRONG, " password wrong...");
        sCodeMsgMap.put(CODE_USER_ALREADY_EXIST, " user already exist...");
        sCodeMsgMap.put(CODE_LOGOUT_ERROR_HAS_NOT_LOGIN, " logout error has not login...");
    }

    public static String getMsg(int code) {
        if(sCodeMsgMap.containsKey(code)) {
            return sCodeMsgMap.get(code);
        } else {
            return " Unknown ";
        }
    }


}
