package com.jichuangtech.clothshopserver.model.vo;

/**
 * Created by Bingo on 2017/8/10.
 */
public class GoodsCartVO {
    /**
     * 购物车的id
     */
    private int id;

    /**
     * 本物品的详细信息
     */
    GoodsVO goodsVO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsVO getGoodsVO() {
        return goodsVO;
    }

    public void setGoodsVO(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
    }
}
