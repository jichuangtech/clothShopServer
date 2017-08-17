package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(GoodsCategoryConstant.API_GOODS_CATEGORY)
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    @RequestMapping()
    public List<GoodsCategoryEntity> list() {
        return mGoodsCategoryRepository.findAll();
    }

    @RequestMapping(value = "/{goodsCategoryId}", method = RequestMethod.GET)
    public GoodsCategoryEntity listById(@PathVariable int goodsCategoryId) {
        System.out.print("listOne goodsCategoryId: " + goodsCategoryId + " \n");
        return mGoodsCategoryRepository.findById(goodsCategoryId);
    }

    @RequestMapping(value = "/{goodsCategoryId}" + GoodsConstant.GOODS, method = RequestMethod.GET)
    public List<GoodsEntity> listGoodsById(@PathVariable int goodsCategoryId) {
        System.out.print("listOne listGoods: " + goodsCategoryId + " \n");
        GoodsCategoryEntity goodsCategory = mGoodsCategoryRepository.findById(goodsCategoryId);
        return goodsCategory.getGoodsList();
    }

    @RequestMapping(value = GoodsCategoryConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public String getGoodsCategoryPicture(HttpServletRequest request,
                                          HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response, SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsCategoryPicture success ...";
    }

}
