package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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


    @RequestMapping(value = GoodsConstant.HOT)
    @ResponseBody
    public List<GoodsEntity> listHot() {
        return mGoodsRepository.findAllByIsHot(new Byte("1"));
    }

    @RequestMapping(value = GoodsConstant.HOT + "/{goodsId}")
    @ResponseBody
    public GoodsEntity listHotById(@PathVariable int goodsId) {
        return mGoodsRepository.findByIsHotAndGoodsId(new Byte("1"), goodsId);
    }

    @RequestMapping("/{goodsId}")
    @ResponseBody
    public GoodsEntity listById(@PathVariable int goodsId) {
        System.out.print("listOne goodsId: " + goodsId +" \n");
        return mGoodsRepository.findByGoodsId(goodsId);
    }

    @RequestMapping(GoodsConstant.PICTURE + "/{picName}")
    @ResponseBody
    public String getGoodsPicture(HttpServletRequest request,
                             HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response,SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsPicture success ...";
    }
}
