package com.jichuangtech.clothshopserver.model.vo;

import java.math.BigDecimal;
import java.util.List;

import com.jichuangtech.clothshopserver.model.GoodsEntity;

public class OrderDetailVO {
	/** 订单总价*/
	private BigDecimal totalAmount;
	List<GoodsVO> GoodsVO;
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

	
}
