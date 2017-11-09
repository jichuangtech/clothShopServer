package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.*;
import com.jichuangtech.clothshopserver.model.vo.AlterCartNumBerVo;
import com.jichuangtech.clothshopserver.model.vo.CartNumberVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartRespVO;
import com.jichuangtech.clothshopserver.repository.GoodsCartRepository;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class GoodsCartService {
    @Autowired
    private GoodsCartRepository mGoodsCartRepository;

    @Autowired
    private GoodsRepository mGoodsRepository;

    /**
     * 查找所有用户的所有购物车
     *
     * @return
     */
    public List<GoodsCartRespVO> getList() {
        List<GoodsCartEntity> goodsCartEntityList = mGoodsCartRepository.findAll();
        return getGoodsCartRespVOs(goodsCartEntityList);
    }

    /**
     * 查找userId用户所有购物车
     *
     * @return
     */
    public List<GoodsCartRespVO> getListByUserId(int userId) {
        List<GoodsCartEntity> goodsCartEntityList = mGoodsCartRepository.findAllByUserId(userId);
        return getGoodsCartRespVOs(goodsCartEntityList);
    }

    public Response alterNumbers(AlterCartNumBerVo vo) {
        Response response = new Response();

        for(CartNumberVO cartNumberVO : vo.cartNumberVOList) {
            alterNumber(cartNumberVO, response);
            if(response.statusCode != 200) {
                break;
            }
        }
        return response;
    }

    private Response alterNumber(CartNumberVO vo, Response response) {
        GoodsCartEntity cart = mGoodsCartRepository.findById(vo.goodsCartId);

        if(cart != null) {
            cart.setGoodsNum(vo.goodsNum);
            mGoodsCartRepository.save(cart);
        } else {
            response.statusCode = -1;
            response.msg = "购物车不存在, id: " + vo.goodsCartId;
        }

        return response;
    }

    public Response saveGoodsCart(GoodsCartReqVO goodsCartVO) {
        Response response = new Response();
        GoodsCartEntity entity = createCart(goodsCartVO);
        if(entity == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CART_ADD_ERROR);
        }
        return response;
    }

    private GoodsCartEntity createCart(GoodsCartReqVO goodsCartVO) {
        Calendar calendar = Calendar.getInstance();
        GoodsCartEntity entity = new GoodsCartEntity();
        GoodsEntity goods = mGoodsRepository.findByGoodsId(goodsCartVO.getGoodsId());
        entity.setUserId(goodsCartVO.getUserId());
        entity.setGoodsId(goods.getGoodsId());
        entity.setGoodsName(goods.getGoodsName());
        entity.setGoodsNum(goodsCartVO.getGoodsNum());
        entity.setAddTime(calendar.getTimeInMillis());
        entity.setGoodsSn(goods.getGoodsSn());
        entity.setSpecId(goodsCartVO.getSpecId());
        entity.setColorId(goodsCartVO.getColorId());
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
    private List<GoodsCartRespVO> getGoodsCartRespVOs(List<GoodsCartEntity> goodsCartEntityList) {
        List<GoodsCartRespVO> goodsCartVOList = new ArrayList<>();
        for (int i = 0; i < goodsCartEntityList.size(); i++) {
            GoodsCartEntity goodsCartEntity = goodsCartEntityList.get(i);
            GoodsCartRespVO goodsVO = createGoodsCartVO(goodsCartEntity);
            goodsCartVOList.add(goodsVO);
        }
        return goodsCartVOList;
    }

    /**
     * goodsCartEntity
     *
     * @param goodsCartEntity
     */
    private GoodsCartRespVO createGoodsCartVO(GoodsCartEntity goodsCartEntity) {
        GoodsCartRespVO goodsCartVO = new GoodsCartRespVO();
        GoodsEntity goodsEntity = mGoodsRepository.findByGoodsId(goodsCartEntity.getGoodsId());
        goodsCartVO.setGoodsId(goodsCartEntity.getGoodsId());
        goodsCartVO.setGoodsSn(goodsCartEntity.getGoodsSn());
        goodsCartVO.setSpecName(goodsCartEntity.getSpecName());
        goodsCartVO.setGoodsNum(goodsCartEntity.getGoodsNum());
        goodsCartVO.setShopPrice(new BigDecimal(goodsCartEntity.getGoodsPrice()));
        goodsCartVO.setGoodsName(goodsCartEntity.getGoodsName());
        goodsCartVO.setColor(goodsCartEntity.getColorName());
        goodsCartVO.setOriginalImg(goodsEntity.getOriginalImg());
        goodsCartVO.setGoodsCartId(goodsCartEntity.getId());
        goodsCartVO.setStoreCount(goodsEntity.getStoreCount());
        goodsCartVO.setColorId(goodsCartEntity.getColorId());
        goodsCartVO.setSpecId(goodsCartEntity.getSpecId());
        return goodsCartVO;
    }

    public Response deleteCart(int cartId) {
        Response response  = new Response();
        GoodsCartEntity entity = mGoodsCartRepository.findById(cartId);
        if(entity != null) {
            mGoodsCartRepository.delete(mGoodsCartRepository.findById(cartId));
        } else {
            response.statusCode = -1;
            response.msg = "要删除购物车不存在, id: " + cartId;
        }
        return response;
    }

    public Response deleteCart(int[] cartIds) {
        Response response = new Response();
        for(int cartId : cartIds) {
            response = deleteCart(cartId);
            if(response.statusCode != 200) {
                break;
            }

        }

        return response;
    }
}
