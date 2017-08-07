package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;

/**
 * Created by Bingo on 2017/8/8.
 */
@Entity
@Table(name = "goods_brand", schema = "clothShop", catalog = "")
public class GoodsBrandEntity {
    private short id;
    private String name;
    private String logo;
    private String desc;
    private String url;
    private Byte sort;
    private String catName;
    private Integer parentCatId;
    private Integer catId;
    private Byte isHot;

    @Id
    @Column(name = "id", nullable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "logo", nullable = true, length = 80)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = -1)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "sort", nullable = true)
    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "cat_name", nullable = true, length = 128)
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Basic
    @Column(name = "parent_cat_id", nullable = true)
    public Integer getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Integer parentCatId) {
        this.parentCatId = parentCatId;
    }

    @Basic
    @Column(name = "cat_id", nullable = true)
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "is_hot", nullable = true)
    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsBrandEntity that = (GoodsBrandEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
        if (catName != null ? !catName.equals(that.catName) : that.catName != null) return false;
        if (parentCatId != null ? !parentCatId.equals(that.parentCatId) : that.parentCatId != null) return false;
        if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
        if (isHot != null ? !isHot.equals(that.isHot) : that.isHot != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        result = 31 * result + (parentCatId != null ? parentCatId.hashCode() : 0);
        result = 31 * result + (catId != null ? catId.hashCode() : 0);
        result = 31 * result + (isHot != null ? isHot.hashCode() : 0);
        return result;
    }
}
