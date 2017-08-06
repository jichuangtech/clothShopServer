package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bingo on 2017/8/5.
 */
@Entity
@Table(name = "goods_spec", schema = "clothShop", catalog = "")
public class GoodsSpecEntity {
    private int id;
    private String name;
    private Byte searchIndex;
    private Integer order;
    private List<GoodsSpecificationEntity> goodsSpecs;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 55)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "search_index", nullable = true)
    public Byte getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(Byte searchIndex) {
        this.searchIndex = searchIndex;
    }

    @Basic
    @Column(name = "order", nullable = true)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsSpecEntity that = (GoodsSpecEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (searchIndex != null ? !searchIndex.equals(that.searchIndex) : that.searchIndex != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (searchIndex != null ? searchIndex.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "specId", fetch = FetchType.EAGER)
    public List<GoodsSpecificationEntity> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpecificationEntity> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }
}
