package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;

/**
 * Created by Bingo on 2017/8/8.
 */
@Entity
@Table(name = "goods_images", schema = "clothShop", catalog = "")
public class GoodsImagesEntity {
    private int imgId;
    private int goodsId;
    private String imageUrl;

    @Id
    @Column(name = "img_id", nullable = false)
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
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
    @Column(name = "image_url", nullable = false, length = 255)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsImagesEntity that = (GoodsImagesEntity) o;

        if (imgId != that.imgId) return false;
        if (goodsId != that.goodsId) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imgId;
        result = 31 * result + goodsId;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
