package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCartConstant;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartRespVO;
import com.jichuangtech.clothshopserver.service.GoodsCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */

@Controller
@RequestMapping(GoodsCartConstant.API_GOODS_CART)
public class GoodsCartController {

    @Autowired
    private GoodsCartService mGoodsCartService;

    @RequestMapping(value="/{cartId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable("cartId")int cartId){
        return mGoodsCartService.deleteCart(cartId);
    }

    @RequestMapping(value="/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsCartRespVO> list(@PathVariable("userId")int userId){
        return mGoodsCartService.getListByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response saveGoodsCart(@RequestBody GoodsCartReqVO goodsCartVO) {
        return mGoodsCartService.saveGoodsCart(goodsCartVO);
    }
}
