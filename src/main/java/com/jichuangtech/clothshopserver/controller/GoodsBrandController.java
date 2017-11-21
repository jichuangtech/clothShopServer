package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsBrandConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsBrandEntity;
import com.jichuangtech.clothshopserver.repository.GoodsBrandRepository;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * Created by Bingo on 2017/7/23.
 */

@RestController
@RequestMapping(GoodsBrandConstant.API_GOODS_BRAND)
public class GoodsBrandController {

    @Autowired
    private GoodsBrandRepository mGoodsBrandRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<GoodsBrandEntity> list() {
        return mGoodsBrandRepository.findAll();
    }

    @RequestMapping(value = "/{goodsBrandId}", method = RequestMethod.GET)
    public GoodsBrandEntity listById(@PathVariable int goodsBrandId) {
        System.out.print("listOne goodsBrandId: " + goodsBrandId + " \n");
        return mGoodsBrandRepository.findById(goodsBrandId);
    }

    @RequestMapping(value = GoodsConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public String getGoodsPicture(HttpServletRequest request,
                                  HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.getPicture(response, SERVER_IMAGE_PATH, picName);
        return "getGoodsPicture success ...";
    }
}
