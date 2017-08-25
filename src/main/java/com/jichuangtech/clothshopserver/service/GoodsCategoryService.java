package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.model.GoodsCartEntity;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCategoryReqVO;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Bingo on 2017/8/22.
 */

@Service
public class GoodsCategoryService {
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

}
