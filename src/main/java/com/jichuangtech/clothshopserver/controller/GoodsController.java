package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.IMAGE_SUFFIX;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * Created by Bingo on 2017/7/23.
 */

@Controller
@RequestMapping(GoodsConstant.API_GOODS)
public class GoodsController {

    @Autowired
    private GoodsRepository mGoodsRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "goods";
    }

    @RequestMapping()
    @ResponseBody
    public List<GoodsEntity> list() {
        return mGoodsRepository.findAll();
    }

    @RequestMapping("/{goodsId}")
    @ResponseBody
    public GoodsEntity listById(@PathVariable int goodsId) {
        System.out.print("listOne goodsId: " + goodsId +" \n");
        return mGoodsRepository.findOne(goodsId);
    }

    @RequestMapping(GoodsConstant.PICTURE + "/{picName}")
    @ResponseBody
    public String getGoodsPicture(HttpServletRequest request,
                             HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response,SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsPicture success ...";
    }
}
