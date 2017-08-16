package com.jichuangtech.clothshopserver.model.vo;


public class GoodsReqVO {
	/** 商品标识 */
	private int goodsId;
	/** 购买数量 */
	private short goodsNum;
	/** 规格 */
	private int specId;
	/** 颜色 */
	private int colorId;
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public short getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(short goodsNum) {
		this.goodsNum = goodsNum;
	}
	public int getSpecId() {
		return specId;
	}
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	
	
}
