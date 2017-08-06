package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */
@Entity
@Table(name = "goods_category", schema = "clothShop", catalog = "")
public class GoodsCategoryEntity {
    @Id
    private int id;
    private String name;
    private String mobileName;
    private short parentId;
    private String parentIdPath;
    private Byte level;
    private byte sortOrder;
    private byte isShow;
    private String image;
    private Byte isHot;
    private Byte catGroup;
    private Byte commissionRate;
    private List<GoodsEntity> goodsList;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 90)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mobile_name", nullable = true, length = 64)
    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    public short getParentId() {
        return parentId;
    }

    public void setParentId(short parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "parent_id_path", nullable = true, length = 128)
    public String getParentIdPath() {
        return parentIdPath;
    }

    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    @Basic
    @Column(name = "sort_order", nullable = false)
    public byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Basic
    @Column(name = "is_show", nullable = false)
    public byte getIsShow() {
        return isShow;
    }

    public void setIsShow(byte isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 512)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "is_hot", nullable = true)
    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    @Basic
    @Column(name = "cat_group", nullable = true)
    public Byte getCatGroup() {
        return catGroup;
    }

    public void setCatGroup(Byte catGroup) {
        this.catGroup = catGroup;
    }

    @Basic
    @Column(name = "commission_rate", nullable = true)
    public Byte getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Byte commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsCategoryEntity that = (GoodsCategoryEntity) o;

        if (id != that.id) return false;
        if (parentId != that.parentId) return false;
        if (sortOrder != that.sortOrder) return false;
        if (isShow != that.isShow) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (mobileName != null ? !mobileName.equals(that.mobileName) : that.mobileName != null) return false;
        if (parentIdPath != null ? !parentIdPath.equals(that.parentIdPath) : that.parentIdPath != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (isHot != null ? !isHot.equals(that.isHot) : that.isHot != null) return false;
        if (catGroup != null ? !catGroup.equals(that.catGroup) : that.catGroup != null) return false;
        if (commissionRate != null ? !commissionRate.equals(that.commissionRate) : that.commissionRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobileName != null ? mobileName.hashCode() : 0);
        result = 31 * result + (int) parentId;
        result = 31 * result + (parentIdPath != null ? parentIdPath.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (int) sortOrder;
        result = 31 * result + (int) isShow;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (isHot != null ? isHot.hashCode() : 0);
        result = 31 * result + (catGroup != null ? catGroup.hashCode() : 0);
        result = 31 * result + (commissionRate != null ? commissionRate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "catId", fetch = FetchType.EAGER)
    public List<GoodsEntity> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsEntity> goodsList) {
        this.goodsList = goodsList;
    }
}
