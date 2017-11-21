package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
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
    public Response<List<GoodsCategoryEntity>> list() {
        Response<List<GoodsCategoryEntity>> response = new Response<>();
        response.data = mGoodsCategoryRepository.findAll();

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CATEGORY_GET_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/{goodsCategoryId}", method = RequestMethod.GET)
    public Response<GoodsCategoryEntity> listById(@PathVariable int goodsCategoryId) {
        Response<GoodsCategoryEntity> response = new Response<>();
        response.data = mGoodsCategoryService.getCategory(goodsCategoryId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CATEGORY_GET_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/{goodsCategoryId}" + GoodsConstant.GOODS, method = RequestMethod.GET)
    public Response<List<GoodsEntity>> listGoodsById(@PathVariable int goodsCategoryId) {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsCategoryService.listGoods(goodsCategoryId);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CATEGORY_GET_GOODS_ERROR);
        }
        return response;
    }

    /**
     * * 使用InfoController中获取图片的方法
     * @param request
     * @param response
     * @param model
     * @param picName
     * @return
     */
    @Deprecated
    @RequestMapping(value = GoodsCategoryConstant.PICTURE + "/{picName:.+}", method = RequestMethod.GET)
    public String getGoodsCategoryPicture(HttpServletRequest request,
                                          HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.getPicture(response, SERVER_IMAGE_PATH, picName);
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
