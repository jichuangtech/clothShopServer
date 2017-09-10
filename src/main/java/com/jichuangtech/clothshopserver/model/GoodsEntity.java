package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */
@Entity
@Table(name = "goods", schema = "clothShop", catalog = "")
public class GoodsEntity {
    @Id
    private int goodsId;
    private int catId;
    private Integer extendCatId;
    private String goodsSn;
    private String goodsName;
    private int clickCount;
    private short brandId;
    private short storeCount;
    private Short commentCount;
    private int weight;
    private BigDecimal marketPrice;
    private BigDecimal shopPrice;
    private BigDecimal costPrice;
    private String keywords;
    private String goodsRemark;   //简单描述
    private String goodsContent;  //详细描述
    private String originalImg;
    private byte isReal;
    private byte isOnSale;
    private byte isFreeShipping;
    private int onTime;
    private short sort;
    private byte isRecommend;
    private byte isNew;
    private Byte isHot;
    private int lastUpdate;
    private short goodsType;
    private Short specType;
    private Integer giveIntegral;
    private int exchangeIntegral;
    private short suppliersId;
    private Integer salesSum;
    private Byte promType;
    private Integer promId;
    private BigDecimal commission;
//    private String spu;
//    private String sku;
    private String shippingAreaIds;
    private List<GoodsSpecificationEntity> goodsSpecs;
    private List<GoodsImagesEntity> goodsDetailImages;
    private List<GoodsColorEntity> goodsColors;


    @Id
    @Column(name = "goods_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "cat_id", nullable = false)
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "extend_cat_id", nullable = true)
    public Integer getExtendCatId() {
        return extendCatId;
    }

    public void setExtendCatId(Integer extendCatId) {
        this.extendCatId = extendCatId;
    }

    @Basic
    @Column(name = "goods_sn", nullable = false, length = 60)
    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    @Basic
    @Column(name = "goods_name", nullable = false, length = 120)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "click_count", nullable = true)
    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    @Basic
    @Column(name = "brand_id", nullable = false)
    public short getBrandId() {
        return brandId;
    }

    public void setBrandId(short brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "store_count", nullable = false)
    public short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(short storeCount) {
        this.storeCount = storeCount;
    }

    @Basic
    @Column(name = "comment_count", nullable = true)
    public Short getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Short commentCount) {
        this.commentCount = commentCount;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "market_price", nullable = true, precision = 2)
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Basic
    @Column(name = "shop_price", nullable = true, precision = 2)
    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    @Basic
    @Column(name = "cost_price", nullable = true, precision = 2)
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    @Basic
    @Column(name = "keywords", nullable = true, length = 255)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "goods_remark", nullable = false, length = 255)
    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    @Basic
    @Column(name = "goods_content", nullable = true, length = -1)
    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    @Basic
    @Column(name = "original_img", nullable = false, length = 255)
    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    @Basic
    @Column(name = "is_real", nullable = true)
    public byte getIsReal() {
        return isReal;
    }

    public void setIsReal(byte isReal) {
        this.isReal = isReal;
    }

    @Basic
    @Column(name = "is_on_sale", nullable = true)
    public byte getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(byte isOnSale) {
        this.isOnSale = isOnSale;
    }

    @Basic
    @Column(name = "is_free_shipping", nullable = true)
    public byte getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(byte isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    @Basic
    @Column(name = "on_time", nullable = true)
    public int getOnTime() {
        return onTime;
    }

    public void setOnTime(int onTime) {
        this.onTime = onTime;
    }

    @Basic
    @Column(name = "sort", nullable = true)
    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "is_recommend", nullable = false)
    public byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Basic
    @Column(name = "is_new", nullable = true)
    public byte getIsNew() {
        return isNew;
    }

    public void setIsNew(byte isNew) {
        this.isNew = isNew;
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
    @Column(name = "last_update", nullable = true)
    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "goods_type", nullable = true)
    public short getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(short goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "spec_type", nullable = true)
    public Short getSpecType() {
        return specType;
    }

    public void setSpecType(Short specType) {
        this.specType = specType;
    }

    @Basic
    @Column(name = "give_integral", nullable = true)
    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    @Basic
    @Column(name = "exchange_integral", nullable = true)
    public int getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(int exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    @Basic
    @Column(name = "suppliers_id", nullable = true)
    public short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(short suppliersId) {
        this.suppliersId = suppliersId;
    }

    @Basic
    @Column(name = "sales_sum", nullable = true)
    public Integer getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Integer salesSum) {
        this.salesSum = salesSum;
    }

    @Basic
    @Column(name = "prom_type", nullable = true)
    public Byte getPromType() {
        return promType;
    }

    public void setPromType(Byte promType) {
        this.promType = promType;
    }

    @Basic
    @Column(name = "prom_id", nullable = true)
    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    @Basic
    @Column(name = "commission", nullable = true, precision = 2)
    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

//    @Basic
//    @Column(name = "spu", nullable = true, length = 128)
//    public String getSpu() {
//        return spu;
//    }
//
//    public void setSpu(String spu) {
//        this.spu = spu;
//    }
//
//    @Basic
//    @Column(name = "sku", nullable = true, length = 128)
//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }

    @Basic
    @Column(name = "shipping_area_ids", nullable = true, length = 255)
    public String getShippingAreaIds() {
        return shippingAreaIds;
    }

    public void setShippingAreaIds(String shippingAreaIds) {
        this.shippingAreaIds = shippingAreaIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (goodsId != that.goodsId) return false;
        if (catId != that.catId) return false;
        if (clickCount != that.clickCount) return false;
        if (brandId != that.brandId) return false;
        if (storeCount != that.storeCount) return false;
        if (weight != that.weight) return false;
        if (isReal != that.isReal) return false;
        if (isOnSale != that.isOnSale) return false;
        if (isFreeShipping != that.isFreeShipping) return false;
        if (onTime != that.onTime) return false;
        if (sort != that.sort) return false;
        if (isRecommend != that.isRecommend) return false;
        if (isNew != that.isNew) return false;
        if (lastUpdate != that.lastUpdate) return false;
        if (goodsType != that.goodsType) return false;
        if (exchangeIntegral != that.exchangeIntegral) return false;
        if (suppliersId != that.suppliersId) return false;
        if (extendCatId != null ? !extendCatId.equals(that.extendCatId) : that.extendCatId != null) return false;
        if (goodsSn != null ? !goodsSn.equals(that.goodsSn) : that.goodsSn != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (commentCount != null ? !commentCount.equals(that.commentCount) : that.commentCount != null) return false;
        if (marketPrice != null ? !marketPrice.equals(that.marketPrice) : that.marketPrice != null) return false;
        if (shopPrice != null ? !shopPrice.equals(that.shopPrice) : that.shopPrice != null) return false;
        if (costPrice != null ? !costPrice.equals(that.costPrice) : that.costPrice != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (goodsRemark != null ? !goodsRemark.equals(that.goodsRemark) : that.goodsRemark != null) return false;
        if (goodsContent != null ? !goodsContent.equals(that.goodsContent) : that.goodsContent != null) return false;
        if (originalImg != null ? !originalImg.equals(that.originalImg) : that.originalImg != null) return false;
        if (isHot != null ? !isHot.equals(that.isHot) : that.isHot != null) return false;
        if (specType != null ? !specType.equals(that.specType) : that.specType != null) return false;
        if (giveIntegral != null ? !giveIntegral.equals(that.giveIntegral) : that.giveIntegral != null) return false;
        if (salesSum != null ? !salesSum.equals(that.salesSum) : that.salesSum != null) return false;
        if (promType != null ? !promType.equals(that.promType) : that.promType != null) return false;
        if (promId != null ? !promId.equals(that.promId) : that.promId != null) return false;
        if (commission != null ? !commission.equals(that.commission) : that.commission != null) return false;
//        if (spu != null ? !spu.equals(that.spu) : that.spu != null) return false;
//        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        if (shippingAreaIds != null ? !shippingAreaIds.equals(that.shippingAreaIds) : that.shippingAreaIds != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId;
        result = 31 * result + catId;
        result = 31 * result + (extendCatId != null ? extendCatId.hashCode() : 0);
        result = 31 * result + (goodsSn != null ? goodsSn.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + clickCount;
        result = 31 * result + (int) brandId;
        result = 31 * result + (int) storeCount;
        result = 31 * result + (commentCount != null ? commentCount.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (marketPrice != null ? marketPrice.hashCode() : 0);
        result = 31 * result + (shopPrice != null ? shopPrice.hashCode() : 0);
        result = 31 * result + (costPrice != null ? costPrice.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (goodsRemark != null ? goodsRemark.hashCode() : 0);
        result = 31 * result + (goodsContent != null ? goodsContent.hashCode() : 0);
        result = 31 * result + (originalImg != null ? originalImg.hashCode() : 0);
        result = 31 * result + (int) isReal;
        result = 31 * result + (int) isOnSale;
        result = 31 * result + (int) isFreeShipping;
        result = 31 * result + onTime;
        result = 31 * result + (int) sort;
        result = 31 * result + (int) isRecommend;
        result = 31 * result + (int) isNew;
        result = 31 * result + (isHot != null ? isHot.hashCode() : 0);
        result = 31 * result + lastUpdate;
        result = 31 * result + (int) goodsType;
        result = 31 * result + (specType != null ? specType.hashCode() : 0);
        result = 31 * result + (giveIntegral != null ? giveIntegral.hashCode() : 0);
        result = 31 * result + exchangeIntegral;
        result = 31 * result + (int) suppliersId;
        result = 31 * result + (salesSum != null ? salesSum.hashCode() : 0);
        result = 31 * result + (promType != null ? promType.hashCode() : 0);
        result = 31 * result + (promId != null ? promId.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
//        result = 31 * result + (spu != null ? spu.hashCode() : 0);
//        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (shippingAreaIds != null ? shippingAreaIds.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "goodsId", fetch = FetchType.EAGER)
    public List<GoodsSpecificationEntity> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpecificationEntity> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }


    @OneToMany(mappedBy = "goodsId", fetch = FetchType.EAGER)
    public List<GoodsImagesEntity> getGoodsDetailImages() {
        return goodsDetailImages;
    }

    public void setGoodsDetailImages(List<GoodsImagesEntity> goodsDetailImages) {
        this.goodsDetailImages = goodsDetailImages;
    }

    @OneToMany(mappedBy = "goodsId", fetch = FetchType.EAGER)
    public List<GoodsColorEntity> getGoodsColors() {
        return goodsColors;
    }

    public void setGoodsColors(List<GoodsColorEntity> goodsColors) {
        this.goodsColors = goodsColors;
    }

    public GoodsSpecificationEntity getGoodsSpec(int specId) {

        for(GoodsSpecificationEntity spec : goodsSpecs) {
            if(spec.getSpecId() == specId) {
                return spec;
            }
        }
        return null;
    }

    public GoodsColorEntity getGoodsColor(int colorId) {

        for(GoodsColorEntity color : goodsColors) {
            if(color.getColorId() == colorId) {
                return color;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "goodsId=" + goodsId +
                ", catId=" + catId +
                ", extendCatId=" + extendCatId +
                ", goodsSn='" + goodsSn + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", clickCount=" + clickCount +
                ", brandId=" + brandId +
                ", storeCount=" + storeCount +
                ", commentCount=" + commentCount +
                ", weight=" + weight +
                ", marketPrice=" + marketPrice +
                ", shopPrice=" + shopPrice +
                ", costPrice=" + costPrice +
                ", keywords='" + keywords + '\'' +
                ", goodsRemark='" + goodsRemark + '\'' +
                ", goodsContent='" + goodsContent + '\'' +
                ", originalImg='" + originalImg + '\'' +
                ", isReal=" + isReal +
                ", isOnSale=" + isOnSale +
                ", isFreeShipping=" + isFreeShipping +
                ", onTime=" + onTime +
                ", sort=" + sort +
                ", isRecommend=" + isRecommend +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", lastUpdate=" + lastUpdate +
                ", goodsType=" + goodsType +
                ", specType=" + specType +
                ", giveIntegral=" + giveIntegral +
                ", exchangeIntegral=" + exchangeIntegral +
                ", suppliersId=" + suppliersId +
                ", salesSum=" + salesSum +
                ", promType=" + promType +
                ", promId=" + promId +
                ", commission=" + commission +
                ", shippingAreaIds='" + shippingAreaIds + '\'' +
                ", goodsSpecs=" + goodsSpecs +
                ", goodsDetailImages=" + goodsDetailImages +
                ", goodsColors=" + goodsColors +
                '}';
    }
}
