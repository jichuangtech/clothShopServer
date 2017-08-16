package com.jichuangtech.clothshopserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * order_goods表
 * @author zxx
 *
 */
@Entity
@Table(name = "order_goods", schema = "clothShop", catalog = "")
public class OrderGoodsEntity {
	private int recId;
	private int orderId;
	private int goodsId;
	private short goodsNum;
	private String specKey;
	//商品总价
	private double goodsPrice;
	private int colorId;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id", nullable = false)
	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	@Basic
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Basic
    @Column(name = "goods_id", nullable = false)
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	@Basic
    @Column(name = "goods_num", nullable = false)
	public short getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(short goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Basic
    @Column(name = "goods_price", nullable = false)
	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


	@Column(name = "spec_key", nullable = false)
	public String getSpecKey() {
		return specKey;
	}

	public void setSpecKey(String specKey) {
		this.specKey = specKey;
	}
	
	@Basic
	@Column(name = "color_id", nullable = false)
	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	
	
}
