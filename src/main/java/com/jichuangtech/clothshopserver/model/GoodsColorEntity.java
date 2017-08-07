package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;

/**
 * Created by Bingo on 2017/8/8.
 */
@Entity
@Table(name = "goods_color", schema = "clothShop", catalog = "")
public class GoodsColorEntity {
    private int id;
    private String colorName;
    private int goodsId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "color_name", nullable = true, length = 55)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Basic
    @Column(name = "goods_id", nullable = false)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsColorEntity that = (GoodsColorEntity) o;

        if (id != that.id) return false;
        if (goodsId != that.goodsId) return false;
        if (colorName != null ? !colorName.equals(that.colorName) : that.colorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (colorName != null ? colorName.hashCode() : 0);
        result = 31 * result + goodsId;
        return result;
    }
}
