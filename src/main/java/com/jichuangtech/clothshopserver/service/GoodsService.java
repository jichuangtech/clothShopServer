package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.model.*;
import com.jichuangtech.clothshopserver.model.vo.GoodsAddVO;
import com.jichuangtech.clothshopserver.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.LogManager;

/**
 * Created by Bingo on 2017/9/9.
 */

@Service
public class GoodsService {
    private static Logger LOGGER = LoggerFactory.getLogger(GoodsService.class.getSimpleName());
    @Autowired
    private ColorRepository mColorRepository;
    @Autowired
    private GoodsColorRepository mGoodsColorRepository;

    @Autowired
    private GoodsSpecRepository mGoodsSpecRepository;
    @Autowired
    private GoodsSpecificationRepository mGoodsSpecificationRepository;

    @Autowired
    private GoodsRepository mGoodsRepository;

    public int saveGoods(GoodsAddVO goodsAddVO) {
        int code = 200;
        //（1）保存到商品
        int goodsId = saveGoodsEntity(goodsAddVO);
        LOGGER.info(" saveGoods goodsId: " + goodsId);
        if(goodsId < 0) {
            return -1;
        }
        //（2）颜色的保存
        code = saveGoodsColors(goodsId, goodsAddVO);
        LOGGER.info("saveGoodsColors code: " + code);
        if(code == 200) {
            //（3）规格的保存
            code = saveGoodsSpec(goodsId, goodsAddVO);
            LOGGER.info(" saveGoodsSpec code: " + code);
        }

        //（4）图片
        // TODO: 2017/9/9  
        //（5）参数图片介绍
        // TODO: 2017/9/9  
        return code;
    }

    private int saveGoodsSpec(int goodsId, GoodsAddVO vo) {
        int code = 200;
        for(GoodsAddVO.Spec spec : vo.getSpecs()) {
            GoodsSpecEntity specEntity = mGoodsSpecRepository.findOne(spec.getSpecId());

            if(specEntity != null) {
                GoodsSpecificationEntity entity = new GoodsSpecificationEntity();
                entity.setGoodsId(goodsId);
                entity.setSpecId(specEntity.getId());
                entity.setSpecName(specEntity.getName());
                entity.setSpecPrice(spec.getPrice());
                mGoodsSpecificationRepository.save(entity);
            } else {
                code = -1;
                return code;
            }
        }

        return code;
    }

    private int saveGoodsColors(int goodsId, GoodsAddVO vo) {
        int code = 200;
        for(int color : vo.getColorIds()) {
            ColorEntity colorEntity = mColorRepository.findOne(color);
            if(colorEntity != null) {
                GoodsColorEntity entity = new GoodsColorEntity();
                entity.setColorId(color);
                entity.setColorName(colorEntity.getColorName());
                entity.setGoodsId(goodsId);
                mGoodsColorRepository.save(entity);
            } else {
                code = -1;
                return code;
            }
        }
        return code;
    }

    private int saveGoodsEntity(GoodsAddVO vo) {
        GoodsEntity entity = new GoodsEntity();
        entity.setGoodsName(vo.getGoodsName());
        entity.setCatId(vo.getCategoryId());
        entity.setGoodsSn(vo.getGoodsSn());
        entity.setBrandId((short) 1);
        entity.setGoodsRemark(vo.getGoodsRemark());
        entity.setGoodsContent(vo.getGoodsContent());
        entity.setStoreCount(vo.getStoreCount());
        entity.setIsHot((byte) vo.getIsHot());
        entity.setIsRecommend((byte) vo.getIsRecommend());

        entity.setOriginalImg("");
        GoodsEntity newGoods = mGoodsRepository.save(entity);
        LOGGER.info("saveGoodsEntity newGoods: " + newGoods);
        return newGoods != null ? newGoods.getGoodsId() : -1;
    }


}
