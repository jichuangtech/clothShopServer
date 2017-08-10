package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.model.*;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsVO;
import com.jichuangtech.clothshopserver.model.vo.OrderDetailVO;
import com.jichuangtech.clothshopserver.repository.GoodsCartRepository;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderGoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GoodsCartService {
    @Autowired
    private GoodsCartRepository mGoodsCartRepository;

    @Autowired
    private GoodsRepository mGoodsRepository;

    /**
     * 查找用户所有购物车
     *
     * @return
     */
    public List<GoodsVO> getList(int userId) {
        List<GoodsCartEntity> goodsCartEntityList = mGoodsCartRepository.findAllByUserId(userId);
        return getGoodsVOs(goodsCartEntityList);
    }

    public Response saveGoodsCart(GoodsCartVO goodsCartVO) {
        Response response = new Response();
        GoodsCartEntity entity = createCart(goodsCartVO);
        if(entity == null) {
            response.statusCode = -1;
        }
        return response;
    }

    private GoodsCartEntity createCart(GoodsCartVO goodsCartVO) {
        Calendar calendar = Calendar.getInstance();
        GoodsCartEntity entity = new GoodsCartEntity();
        GoodsEntity goods = mGoodsRepository.findByGoodsId(goodsCartVO.getGoodsId());
        entity.setUserId(goodsCartVO.getUserId());
        entity.setGoodsId(goods.getGoodsId());
        entity.setGoodsName(goods.getGoodsName());
        entity.setGoodsNum(goodsCartVO.getGoodsNum());
        entity.setAddTime(calendar.getTimeInMillis());
        entity.setGoodsSn(goods.getGoodsSn());
        GoodsSpecificationEntity goodsSpecEntity = goods.getGoodsSpec(goodsCartVO.getSpecId());
        if(goodsSpecEntity != null) {
            entity.setSpecName(goodsSpecEntity.getSpecName());
            entity.setGoodsPrice(goodsSpecEntity.getSpecPrice());
        }

        GoodsColorEntity color = goods.getGoodsColor(goodsCartVO.getColorId());
        if(color != null) {
            entity.setColorName(color.getColorName());
        }

        return mGoodsCartRepository.save(entity);
    }

    /**
     * 获取购物车的详细信息
     *
     * @param goodsCartEntityList
     * @return
     */
    private List<GoodsVO> getGoodsVOs(List<GoodsCartEntity> goodsCartEntityList) {
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (int i = 0; i < goodsCartEntityList.size(); i++) {
            GoodsCartEntity goodsCartEntity = goodsCartEntityList.get(i);
            GoodsVO goodsVO = createGoodsCartVO(goodsCartEntity);
            goodsVOList.add(goodsVO);
        }
        return goodsVOList;
    }

    /**
     * goodsCartEntity
     *
     * @param goodsCartEntity
     */
    private GoodsVO createGoodsCartVO(GoodsCartEntity goodsCartEntity) {
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setGoodsId(goodsCartEntity.getGoodsId());
        goodsVO.setGoodsSn(goodsCartEntity.getGoodsSn());
        goodsVO.setSpecName(goodsCartEntity.getSpecName());
        goodsVO.setGoodsNum(goodsCartEntity.getGoodsNum());
        goodsVO.setGoodsPrice(goodsCartEntity.getGoodsPrice());
        goodsVO.setGoodsName(goodsCartEntity.getGoodsName());
        goodsVO.setColor(goodsCartEntity.getColorName());
        return goodsVO;
    }

    public Response deleteCart(int cartId) {
        GoodsCartEntity entity = mGoodsCartRepository.findById(cartId);
        if(entity != null) {
            mGoodsCartRepository.delete(mGoodsCartRepository.findById(cartId));
        }
        return new Response();
    }
}
