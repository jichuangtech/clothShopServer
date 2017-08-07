package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsBrandConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsBrandEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsBrandRepository;
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
@RequestMapping(GoodsBrandConstant.API_GOODS_BRAND)
public class GoodsBrandController {

    @Autowired
    private GoodsBrandRepository mGoodsBrandRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "goodsBrand";
    }

    @RequestMapping()
    @ResponseBody
    public List<GoodsBrandEntity> list() {
        return mGoodsBrandRepository.findAll();
    }

    @RequestMapping("/{goodsBrandId}")
    @ResponseBody
    public GoodsBrandEntity listById(@PathVariable int goodsBrandId) {
        System.out.print("listOne goodsBrandId: " + goodsBrandId +" \n");
        return mGoodsBrandRepository.findById(goodsBrandId);
    }

    @RequestMapping(GoodsConstant.PICTURE + "/{picName}")
    @ResponseBody
    public String getGoodsPicture(HttpServletRequest request,
                             HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response,SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsPicture success ...";
    }
}
