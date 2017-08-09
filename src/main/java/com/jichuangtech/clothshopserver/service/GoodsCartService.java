package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.model.GoodsCartEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;
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

    /**
     * 查找用户所有购物车
     *
     * @return
     */
    public List<GoodsCartVO> getList(int userId) {
        List<GoodsCartEntity> goodsCartEntityList = mGoodsCartRepository.findAllByUserId(userId);
        return getGoodsCartDetailInfo(goodsCartEntityList);
    }

    public GoodsCartVO saveGoodsCart(int userId, GoodsCartVO goodsCartVO) {
        GoodsCartEntity entity = createCart(userId, goodsCartVO);
        goodsCartVO.setId(entity.getId());
        return goodsCartVO;
    }

    private GoodsCartEntity createCart(int userId, GoodsCartVO goodsCartVO) {
        Calendar calendar = Calendar.getInstance();
        GoodsCartEntity entity = new GoodsCartEntity();
        GoodsVO goodsVO = goodsCartVO.getGoodsVO();
        entity.setUserId(userId);
        entity.setGoodsId(goodsVO.getGoodsId());
        entity.setGoodsName(goodsVO.getGoodsName());
        entity.setGoodsNum(goodsVO.getGoodsNum());
        entity.setGoodsPrice(goodsVO.getGoodsPrice());
        entity.setAddTime(calendar.getTimeInMillis());
        entity.setSpecName(goodsVO.getSpecName());
        entity.setGoodsSn(goodsVO.getGoodsSn());
        return mGoodsCartRepository.save(entity);
    }


    /**
     * 获取购物车的详细信息
     *
     * @param goodsCartEntityList
     * @return
     */
    private List<GoodsCartVO> getGoodsCartDetailInfo(List<GoodsCartEntity> goodsCartEntityList) {
        List<GoodsCartVO> goodsCartVOList = new ArrayList<>();
        for (int i = 0; i < goodsCartEntityList.size(); i++) {
            GoodsCartEntity goodsCartEntity = goodsCartEntityList.get(i);
            GoodsCartVO goodsCartVO = createGoodsCartVO(goodsCartEntity);
            goodsCartVOList.add(goodsCartVO);
        }
        return goodsCartVOList;
    }

    /**
     * goodsCartEntity
     *
     * @param goodsCartEntity
     */
    private GoodsCartVO createGoodsCartVO(GoodsCartEntity goodsCartEntity) {
        GoodsCartVO goodsCartVO = new GoodsCartVO();
        goodsCartVO.setId(goodsCartEntity.getId());
        GoodsVO goodsVO = new GoodsVO();

        goodsVO.setGoodsId(goodsCartEntity.getGoodsId());
        goodsVO.setGoodsSn(goodsCartEntity.getGoodsSn());
        goodsVO.setSpecName(goodsCartEntity.getSpecName());
        goodsVO.setGoodsNum(goodsCartEntity.getGoodsNum());
        goodsVO.setGoodsPrice(goodsCartEntity.getGoodsPrice());
        goodsVO.setGoodsName(goodsCartEntity.getGoodsName());

        goodsCartVO.setGoodsVO(goodsVO);
        return goodsCartVO;
    }

}
