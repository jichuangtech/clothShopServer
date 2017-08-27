package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.GoodsCartEntity;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCategoryReqVO;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Bingo on 2017/8/22.
 */

@Service
public class GoodsCategoryService {
    private static Logger LOGGER  = LoggerFactory.getLogger(GoodsCategoryService.class.getSimpleName());
    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    public GoodsCategoryEntity getCategory(int goodsCategoryId) {
        return mGoodsCategoryRepository.findById(goodsCategoryId);
    }

    public List<GoodsEntity> listGoods(@PathVariable int goodsCategoryId) {
        GoodsCategoryEntity goodsCategory = getCategory(goodsCategoryId);
        return goodsCategory.getGoodsList();
    }

    public Response saveGoodsCategory(GoodsCategoryReqVO vo) {
        Response response = new Response();
        try {
            GoodsCategoryEntity entity = new GoodsCategoryEntity();
            entity.setName(vo.getName());
            entity.setImage(vo.getImage());
            mGoodsCategoryRepository.save(entity);
        } catch (Exception e) {
            response.statusCode = -1;
            response.msg = "添加商品分类失败";
        }
        return response;
    }

    public int delete(int id) {
        int code = ResponseCode.CODE_SUCCESS;
        GoodsCategoryEntity entity = mGoodsCategoryRepository.findById(id);
        LOGGER.info(" delete id: " + id + ", entity: " + entity);

        if(entity != null) {
            mGoodsCategoryRepository.delete(entity);
        } else {
            code = ResponseCode.CODE_DELETE_NOT_FOUND;
        }

        return code;
    }


}
