package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Bingo on 2017/9/10.
 */
@Entity
@Table(name = "goods_specification", schema = "clothShop", catalog = "")
public class GoodsSpecificationEntity {
    private int id;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int goodsId;

    @Basic
    @Column(name = "goods_id", nullable = false)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    private int specId;

    @Basic
    @Column(name = "spec_id", nullable = false)
    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    private String specName;


    @Basic
    @Column(name = "spec_name", nullable = true, length = 55)
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    private Double specPrice;

    @Basic
    @Column(name = "spec_price", nullable = true, precision = 2)
    public Double getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(Double specPrice) {
        this.specPrice = specPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsSpecificationEntity entity = (GoodsSpecificationEntity) o;

        if (id != entity.id) return false;
        if (goodsId != entity.goodsId) return false;
        if (specId != entity.specId) return false;
        if (specName != null ? !specName.equals(entity.specName) : entity.specName != null) return false;
        if (specPrice != null ? !specPrice.equals(entity.specPrice) : entity.specPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + goodsId;
        result = 31 * result + specId;
        result = 31 * result + (specName != null ? specName.hashCode() : 0);
        result = 31 * result + (specPrice != null ? specPrice.hashCode() : 0);
        return result;
    }
}
