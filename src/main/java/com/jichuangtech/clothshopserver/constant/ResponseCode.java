package com.jichuangtech.clothshopserver.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bingo on 2017/8/27.
 */
public class ResponseCode {

    public static final int CODE_SUCCESS = 200;

    public static final int CODE_DELETE_NOT_FOUND = 201;

    //商品系列返回码
    public static final int COODE_GOODS_x = 100;
    public static final int COODE_GOODS_ADD_ERROR = 101;

    //商品分类系列返回码
    public static final int COODE_GOODS_CATEGORY_D = 300;

    //购物车分类系列返回码
    public static final int COODE_GOODS_CART_x = 400;

    //订单系列返回码
    public static final int COODE_ORDER_x = 500;

    //收货地址系列返回码
    public static final int COODE_ADDRESS_x = 600;

    public static final Map<Integer, String> sCodeMsgMap = new HashMap();

    static {
        sCodeMsgMap.put(CODE_SUCCESS, " success ");
        sCodeMsgMap.put(CODE_DELETE_NOT_FOUND, "goods cart not found ");
    }

    public static String getMsg(int code) {
        if(sCodeMsgMap.containsKey(code)) {
            return sCodeMsgMap.get(code);
        } else {
            return " Unknown ";
        }
    }


}
