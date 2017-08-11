package com.jichuangtech.clothshopserver.model.vo;

/**
 * Created by Bingo on 2017/8/10.
 */
public class GoodsCartReqVO {
    /**
     * 购物车的id
     */
    private int id;

    /**
     * 用户Id
     */

    private int userId;

    /**
     * 商品id
     */
    private int goodsId;

    /**
     * 颜色id
     */
    private int colorId;

    /**
     * 货物数量
     */
    private short goodsNum;

    /**
     * 规格Id
     */
    private int specId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public short getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(short goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

}
