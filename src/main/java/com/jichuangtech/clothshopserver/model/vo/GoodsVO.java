package com.jichuangtech.clothshopserver.model.vo;

import java.math.BigDecimal;

public class GoodsVO {
	/**本店价 */
	private BigDecimal goodsPrice;
	/** 购买数量 */
	private short goodsNum;
	private String goodsName;
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public short getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(short goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
}