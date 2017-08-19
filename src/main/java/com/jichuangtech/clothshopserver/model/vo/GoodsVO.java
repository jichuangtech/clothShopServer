package com.jichuangtech.clothshopserver.model.vo;

import java.math.BigDecimal;

public class GoodsVO {
	/** 商品标识 */
	private int goodsId;
	/**本店价 */
	private BigDecimal shopPrice;
	/** 购买数量 */
	private short goodsNum;
	/** 商品名称*/
	private String goodsName;
	/** 规格 */
	private String specName;
	/** 颜色 */
	private String color;
	/** 商品编号  */
	private String goodsSn;
	/** 商品主图片名 */
	private String originalImg;


	
	
	public BigDecimal getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getOriginalImg() {
		return originalImg;
	}
	public void setOriginalImg(String originalImg) {
		this.originalImg = originalImg;
	}
	
}
