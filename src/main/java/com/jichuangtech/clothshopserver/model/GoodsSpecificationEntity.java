package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;

/**
 * Created by Bingo on 2017/8/5.
 */
@Entity
@Table(name = "goods_specification", schema = "clothShop", catalog = "")
public class GoodsSpecificationEntity {
    private int id;
    private int goodsId;
    private int specId;
    private String specName;
    private double specPrice;

    @Column(name = "spec_price")
    public double getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(double specPrice) {
        this.specPrice = specPrice;
    }

    @Column(name = "spec_name", nullable = false)
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "spec_id", nullable = false)
    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsSpecificationEntity that = (GoodsSpecificationEntity) o;

        if (id != that.id) return false;
        if (goodsId != that.goodsId) return false;
        if (specId != that.specId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + goodsId;
        result = 31 * result + specId;
        return result;
    }
}
