package com.jichuangtech.clothshopserver.model.vo;

import java.math.BigDecimal;
import java.util.List;

import com.jichuangtech.clothshopserver.model.GoodsEntity;

public class OrderDetailVO {
	/** 订单总价*/
	private BigDecimal totalAmount;
	/** 地址*/
	private String address;
	/** 手机号*/
	private String mobile;
	/** 收货人*/
	private String consignee;
	/** 订单编号*/
	private String orderSn;
	/** 订单号*/
	private int orderId;
	/** 订单状态*/
	private byte orderStatus;
	
	private List<GoodsVO> GoodsVO;
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<GoodsVO> getGoodsVO() {
		return GoodsVO;
	}
	public void setGoodsVO(List<GoodsVO> goodsVO) {
		GoodsVO = goodsVO;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public byte getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "[ mobile: " + mobile + ", consignee: " + consignee +"]";
	}
}
