package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.constant.InfoConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.repository.ColorRepository;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.service.GoodsCategoryService;
import com.jichuangtech.clothshopserver.service.GoodsInfoService;
import com.jichuangtech.clothshopserver.service.GoodsService;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * 进行仅可读性的操作用来GET请求一些公开的数据，不需要进行身份验证例如：
 * （1）商品信息
 * （2）分类信息
 * （3）图片
 * 等...
 * Created by Bingo on 2017/9/9.
 */
@RestController
@RequestMapping(InfoConstant.API_INFO)
public class InfoController {

    @Autowired
    private GoodsRepository mGoodsRepository;

    @Autowired
    private ColorRepository mColorRepository;
    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    @Autowired
    private GoodsCategoryService mGoodsCategoryService;

    @ApiOperation(value = "根据商品Id来获取商品信息", notes = "不需要经过token")
    @RequestMapping(value = InfoConstant.GOODS + "/{goodsId}", method = RequestMethod.GET)
    public Response<GoodsEntity> listById(@PathVariable int goodsId) {
        Response<GoodsEntity> response = new Response<>();
        response.data = mGoodsRepository.findByGoodsId(goodsId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(InfoConstant.COLOR)
    public List<ColorEntity> getColors () {
        return mColorRepository.findAll();
    }

    @RequestMapping(value = InfoConstant.PICTURE + "/{picName:.+}", method = RequestMethod.GET)
    public Response getPicture(HttpServletResponse response, @PathVariable String picName) {
        Response res = new Response();
        res.setStatusCode(PictureUtils.getPicture(response, SERVER_IMAGE_PATH, picName));
        return res;
    }


    @RequestMapping(InfoConstant.GOODS_CATEGORIES)
    public Response<List<GoodsCategoryEntity>> listGoodsCategories() {
        Response<List<GoodsCategoryEntity>> response = new Response<>();
        response.data = mGoodsCategoryRepository.findAll();

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CATEGORY_GET_ERROR);
        }
        return response;
    }

    @ApiOperation(value = "获取某个商品分类下面的商品", notes = "不需要经过token")
    @RequestMapping(value = InfoConstant.GOODS_CATEGORIES + "/{goodsCategoryId}" + InfoConstant.GOODS,
            method = RequestMethod.GET)
    public Response<List<GoodsEntity>> listGoodsFromCateById(@PathVariable int goodsCategoryId) {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsCategoryService.listGoods(goodsCategoryId);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_CATEGORY_GET_GOODS_ERROR);
        }
        return response;
    }


    @RequestMapping(value = InfoConstant.GOODS, method = RequestMethod.GET)
    public Response<List<GoodsEntity>> listGoods() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAll();

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_GET_ALL_ERROR);
        }

        return response;
    }

    @RequestMapping(value = InfoConstant.GOODS + GoodsConstant.HOT, method = RequestMethod.GET)
    public Response<List<GoodsEntity>> listHotGoods() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAllByIsHot(new Byte("1"));

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_HOT_ERROR);
        }
        return response;
    }

    @RequestMapping(value = InfoConstant.GOODS + GoodsConstant.RECOMMEND, method = RequestMethod.GET)
    @ResponseBody
    public Response<List<GoodsEntity>> listRecommendGoods() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAllByIsRecommend(new Byte("1"));

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_RECOMMEND_ERROR);
        }
        return response;
    }

}
