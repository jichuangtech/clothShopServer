package com.jichuangtech.clothshopserver.model.vo;

/**
 * Created by Bingo on 2017/8/10.
 */
public class GoodsCategoryReqVO {

    /**
     * 商品分类名
     */
    public String name;

    /**
     * 商品分类的图片
     */
    public String image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
