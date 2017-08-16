package com.jichuangtech.clothshopserver.model.vo;

import java.util.List;

public class OrderReqVO {
	/** 地址*/
	private int addressId;
	/** 用户标识*/
	private int userId;
	
	private List<GoodsReqVO> goodsReqVOList;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<GoodsReqVO> getGoodsReqVOList() {
		return goodsReqVOList;
	}

	public void setGoodsReqVOList(List<GoodsReqVO> goodsReqVOList) {
		this.goodsReqVOList = goodsReqVOList;
	}
	
	
}
