package com.jichuangtech.clothshopserver.model.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Bingo on 2017/9/9.
 */
public class GoodsAddVO {
    private int goodsId = -1;
    private String goodsName;

    private int categoryId;

    /**
     * 商品编号
     */
    private String goodsSn;

    private short storeCount;
    private String goodsRemark;   //简单描述
    private String goodsContent;  //详细描述
    private int isRecommend;
    private int isHot;
    //    private List<Spec> specs;
    private List<Integer> colorIds;
    private MultipartFile image; //商品显示图片
    private List<MultipartFile> detailInfoImages;  //详细信息的图片列表
    private String kgPrice;
    private String codePrice;

    public String getKgPrice() {
        return kgPrice;
    }

    public void setKgPrice(String kgPrice) {
        this.kgPrice = kgPrice;
    }

    public String getCodePrice() {
        return codePrice;
    }

    public void setCodePrice(String codePrice) {
        this.codePrice = codePrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(short storeCount) {
        this.storeCount = storeCount;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public List<Integer> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Integer> colorIds) {
        this.colorIds = colorIds;
    }

    public static class Spec {
        private int specId;
        private double price;

        public int getSpecId() {
            return specId;
        }

        public void setSpecId(int specId) {
            this.specId = specId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Spec{" +
                    "specId=" + specId +
                    ", price=" + price +
                    '}';
        }
    }
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
//    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }

    public List<MultipartFile> getDetailInfoImages() {
        return detailInfoImages;
    }

    public void setDetailInfoImages(List<MultipartFile> detailInfoImages) {
        this.detailInfoImages = detailInfoImages;
    }


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "GoodsAddVO{" +
                "goodsId='" + goodsId + '\'' +
                "goodsName='" + goodsName + '\'' +
                ", categoryId=" + categoryId +
                ", goodsSn='" + goodsSn + '\'' +
                ", storeCount=" + storeCount +
                ", goodsRemark='" + goodsRemark + '\'' +
                ", goodsContent='" + goodsContent + '\'' +
                ", isRecommend=" + isRecommend +
                ", isHot=" + isHot +
//                ", specs=" + specs +
                ", colorIds=" + colorIds +
//                ", image='" + image + '\'' +
                ", detailInfoImages=" + detailInfoImages +
                '}';
    }
}
