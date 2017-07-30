package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.IMAGE_SUFFIX;

/**
 * Created by Bingo on 2017/7/23.
 */

@Controller
@RequestMapping(GoodsCategoryConstant.API_GOODS_CATEGORY)
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "goodsCategory";
    }

    @RequestMapping()
    @ResponseBody
    public List<GoodsCategoryEntity> list() {
        return mGoodsCategoryRepository.findAll();
    }

    @RequestMapping("/{goodsCategoryId}")
    @ResponseBody
    public GoodsCategoryEntity listById(@PathVariable int goodsCategoryId) {
        System.out.print("listOne goodsCategoryId: " + goodsCategoryId + " \n");
        return mGoodsCategoryRepository.findOne(goodsCategoryId);
    }

    @RequestMapping("/{goodsCategoryId}" + GoodsConstant.GOODS)
    @ResponseBody
    public List<GoodsEntity> listGoodsById(@PathVariable int goodsCategoryId) {
        System.out.print("listOne listGoods: " + goodsCategoryId + " \n");
        GoodsCategoryEntity goodsCategory = mGoodsCategoryRepository.findOne(goodsCategoryId);
        return goodsCategory.getGoodsList();
    }

//        private static final String SERVER_IMAGE_PATH = "/Users/Bingo/Desktop/shop/clothShop/img/";

    @RequestMapping(GoodsCategoryConstant.PICTURE + "/{picName}")
    @ResponseBody
    public String getPicture(HttpServletRequest request,
                             HttpServletResponse response, Model model, @PathVariable String picName) {
        System.out.println("getPicture :" + picName);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(SERVER_IMAGE_PATH + picName + IMAGE_SUFFIX);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            System.out.println(" getPicture e: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (fis != null) {
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "getPicture success ...";
    }

}
