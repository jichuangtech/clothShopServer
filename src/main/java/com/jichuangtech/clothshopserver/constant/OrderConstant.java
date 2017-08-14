package com.jichuangtech.clothshopserver.constant;

public class OrderConstant extends Constant{
	public static final String API_ORDER = API + "/order";
	
    /** 某用户全部订单*/
    public static final byte ORDER_USER_ALL = 0;
    /** 待支付*/
    public static final byte ORDER_UN_PAID = 1;
    /** 待发货*/
    public static final byte ORDER_UN_SEND = 2;
    /** 待收货*/
    public static final byte ORDER_WAIT_RECEIVE = 3;
    /** 已完成*/
    public static final byte ORDER_DONE = 4;
    
}
