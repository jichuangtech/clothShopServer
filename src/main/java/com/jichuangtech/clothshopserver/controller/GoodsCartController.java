package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCartConstant;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.AlterCartNumBerVO;
import com.jichuangtech.clothshopserver.model.vo.CartNumberVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartRespVO;
import com.jichuangtech.clothshopserver.service.GoodsCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */

@RestController
@RequestMapping(GoodsCartConstant.API_GOODS_CART)
public class GoodsCartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCartController.class);

    @Autowired
    private GoodsCartService mGoodsCartService;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("cartId") int cartId) {
        LOGGER.info("delete user");
        return mGoodsCartService.deleteCart(cartId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodsCartRespVO> list() {
        return mGoodsCartService.getList();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<GoodsCartRespVO> listByUserId(@PathVariable("userId") int userId) {
        return mGoodsCartService.getListByUserId(userId);
    }

    @RequestMapping(value = GoodsCartConstant.GOODS_NUMBER, method = RequestMethod.POST)
    public Response alterNumber(@RequestBody AlterCartNumBerVO vo) {
        return mGoodsCartService.alterNumbers(vo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response saveGoodsCart(@RequestBody GoodsCartReqVO goodsCartVO) {
        return mGoodsCartService.saveGoodsCart(goodsCartVO);
    }
}
