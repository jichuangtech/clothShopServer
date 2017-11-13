package com.jichuangtech.clothshopserver.weixin;

/**
 * 小程序微信支付的配置文件
 * @author 
 *
 */
public class WxPayConfig {
	//小程序appid
	public static final String appid = "wx9f2312ddb9ae2498";
	//微信支付的商户id
	public static final String mch_id = "1487544002";
	//微信支付的商户密钥
	public static final String key = "zxc1992616jsjzxc1992616jsj992616";
	//支付成功后的服务器回调url
	public static final String NOTIFY_URL = "https://www.jichuangtech.site/clothshopserver/api/pay/wxNotify";
	//签名方式，固定值
	public static final String SIGN_TYPE = "MD5";
	//交易类型，小程序支付的固定值为JSAPI
	public static final String TRADE_TYPE = "JSAPI";
	//微信统一下单接口地址
	public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

}