package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCartConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.*;
import com.jichuangtech.clothshopserver.service.GoodsCartService;
import com.jichuangtech.clothshopserver.service.SessionService;
import com.jichuangtech.clothshopserver.service.UsersService;
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

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("cartId") int cartId) {
        LOGGER.info("delete goodsCarts cartId: " + cartId);
        return mGoodsCartService.deleteCart(cartId);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response delete(@RequestBody DeleteCartVO vo) {
        LOGGER.info("delete goodsCarts cartIds: " + vo.cartIds);
        return mGoodsCartService.deleteCart(vo.cartIds);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response<List<GoodsCartRespVO>> list() {
        Response<List<GoodsCartRespVO>> response = new Response<>();
        response.data = mGoodsCartService.getList();

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CART_GET_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<GoodsCartRespVO>> listByUserId(@PathVariable("userId") int userId,
                                              @RequestHeader("access_token") String accessToken) {
        Response<List<GoodsCartRespVO>> response = new Response<>();
        userId = usersService.getUserIdByToken(accessToken);
        response.data = mGoodsCartService.getListByUserId(userId);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CART_GET_ERROR);
        }
        LOGGER.info(" goodsCard listByUserId userId: " + userId);
        return response;
    }

    @RequestMapping(value = GoodsCartConstant.GOODS_NUMBER, method = RequestMethod.POST)
    public Response alterNumber(@RequestBody AlterCartNumBerVo vo) {
        return mGoodsCartService.alterNumbers(vo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response saveGoodsCart(@RequestBody GoodsCartReqVO goodsCartVO,
                                  @RequestHeader("access_token") String accessToken) {
        goodsCartVO.setUserId(usersService.getUserIdByToken(accessToken));
        return mGoodsCartService.saveGoodsCart(goodsCartVO);
    }
}
