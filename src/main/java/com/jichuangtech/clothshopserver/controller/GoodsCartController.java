package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsBrandConstant;
import com.jichuangtech.clothshopserver.constant.GoodsCartConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsBrandEntity;
import com.jichuangtech.clothshopserver.model.GoodsCartEntity;
import com.jichuangtech.clothshopserver.model.vo.GoodsCartVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsVO;
import com.jichuangtech.clothshopserver.model.vo.OrderDetailVO;
import com.jichuangtech.clothshopserver.repository.GoodsBrandRepository;
import com.jichuangtech.clothshopserver.service.GoodsCartService;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.IMAGE_SUFFIX;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * Created by Bingo on 2017/7/23.
 */

@Controller
@RequestMapping(GoodsCartConstant.API_GOODS_CART)
public class GoodsCartController {

    @Autowired
    private GoodsCartService mGoodsCartService;


    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public GoodsCartVO saveGoodsCart(@PathVariable("userId") int userId, @RequestBody GoodsCartVO goodsCartVO) {
        return mGoodsCartService.saveGoodsCart(userId, goodsCartVO);
    }
}
