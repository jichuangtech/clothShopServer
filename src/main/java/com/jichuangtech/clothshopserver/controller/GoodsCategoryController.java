package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.service.GoodsCategoryService;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private GoodsCategoryService mGoodsCategoryService;

    @RequestMapping()
    public List<GoodsCategoryEntity> list() {
        return mGoodsCategoryRepository.findAll();
    }

    @RequestMapping(value = "/{goodsCategoryId}", method = RequestMethod.GET)
    public GoodsCategoryEntity listById(@PathVariable int goodsCategoryId) {
        return mGoodsCategoryService.getCategory(goodsCategoryId);
    }

    @RequestMapping(value = "/{goodsCategoryId}" + GoodsConstant.GOODS, method = RequestMethod.GET)
    public List<GoodsEntity> listGoodsById(@PathVariable int goodsCategoryId) {
        return mGoodsCategoryService.listGoods(goodsCategoryId);
    }

    @RequestMapping(value = GoodsCategoryConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public String getGoodsCategoryPicture(HttpServletRequest request,
                                          HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response, SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsCategoryPicture success ...";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response saveGoodsCategory(String name, MultipartFile image) {
        return mGoodsCategoryService.saveGoodsCategory(name, image);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response deleteGoodsCategory(@ApiParam(name = "要被删除的商品分类的ID", required = true) @RequestParam int categoryId) {
        Response response = new Response();
        int code = mGoodsCategoryService.delete(categoryId);
        response.setStatusCode(code);
        return response;
    }


}
