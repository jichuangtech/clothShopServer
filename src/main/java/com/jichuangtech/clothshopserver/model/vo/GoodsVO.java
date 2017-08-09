package com.jichuangtech.clothshopserver.model.vo;

import java.math.BigDecimal;

public class GoodsVO {
	/**
	 * 规格 , 单价
	 */
	private int goodsId;
	/**本店价 */
	private BigDecimal goodsPrice;
	/** 购买数量 */
	private short goodsNum;
	private String goodsName;
	/**
	 * 规格
	 */
	private String specName;
	/**
	 * 货号
	 */
	private String goodsSn;
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
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}
}
