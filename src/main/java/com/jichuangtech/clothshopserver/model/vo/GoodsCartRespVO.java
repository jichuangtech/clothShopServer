package com.jichuangtech.clothshopserver.model.vo;

/**
 * Created by Bingo on 2017/8/10.
 */
public class GoodsCartRespVO extends GoodsVO{
    /**
     * 购物车的id
     */
    private int goodsCartId;

    /**
     * 图片
     */
    private String originalImg;

    public int getGoodsCartId() {
        return goodsCartId;
    }

    public void setGoodsCartId(int goodsCartId) {
        this.goodsCartId = goodsCartId;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }
}
