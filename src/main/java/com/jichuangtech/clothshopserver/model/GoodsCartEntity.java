package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Bingo on 2017/8/9.
 */
@Entity
@Table(name = "goods_cart", schema = "clothShop", catalog = "")
public class GoodsCartEntity {
    private int id;
    private int userId;
    private int goodsId;
    private String goodsSn;
    private String goodsName;
    private double goodsPrice;
    private short goodsNum;
    private String specName;
    private String colorName;
    private Long addTime;
    private int colorId;
    private int specId;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "color_id", nullable = false)
    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Basic
    @Column(name = "spec_id", nullable = false)
    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "goods_sn", nullable = false, length = 60)
    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    @Basic
    @Column(name = "goods_name", nullable = false, length = 120)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "goods_price", nullable = false, precision = 2)
    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
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
    @Column(name = "color_name", nullable = false)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }


    @Basic
    @Column(name = "spec_name", nullable = false, length = 64)
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }


    @Basic
    @Column(name = "add_time", nullable = true)
    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsCartEntity that = (GoodsCartEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (goodsId != that.goodsId) return false;
        if (goodsNum != that.goodsNum) return false;
        if (goodsSn != null ? !goodsSn.equals(that.goodsSn) : that.goodsSn != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (specName != null ? !specName.equals(that.specName) : that.specName != null) return false;
        if (addTime != null ? !addTime.equals(that.addTime) : that.addTime != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + goodsId;
        result = 31 * result + (goodsSn != null ? goodsSn.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (int) goodsNum;
        result = 31 * result + (specName != null ? specName.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        return result;
    }
}
