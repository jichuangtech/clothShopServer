package com.jichuangtech.clothshopserver.constant;

public class OrderConstant extends Constant{
	public static final String API_ORDER = API + "/order";
    public static final String ORDER_STATUS_LIST = "/orderStatusList";
    public static final String SHIPPING_STATUS_LIST = "/shippingStatusList";
    public static final String PAY_STATUS_LIST = "/payStatusList";
    /** 待支付*/
    public static final byte ORDER_UN_PAID = 1;
    /** 待发货*/
    public static final byte ORDER_UN_SEND = 2;
    /** 待收货*/
    public static final byte ORDER_WAIT_RECEIVE = 3;
    /** 已完成*/
    public static final byte ORDER_DONE = 4;
    
}
