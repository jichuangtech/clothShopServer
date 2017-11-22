package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Page;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.GoodsAddVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsPaginationVO;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.service.GoodsCategoryService;
import com.jichuangtech.clothshopserver.service.GoodsService;
import com.jichuangtech.clothshopserver.utils.PaginationUtils;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(GoodsConstant.API_GOODS)
public class GoodsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsRepository mGoodsRepository;

    @Autowired
    private GoodsCategoryService mGoodsCategoryService;

    @Autowired
    private GoodsService mGoodsService;

    @RequestMapping(method = RequestMethod.GET)
    public Response<List<GoodsEntity>> list() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAll();

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_GET_ALL_ERROR);
        }

        return response;
    }

    @RequestMapping(value = GoodsConstant.HOT, method = RequestMethod.GET)
    public Response<List<GoodsEntity>> listHot() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAllByIsHot(new Byte("1"));

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_HOT_ERROR);
        }
        return response;
    }

    @RequestMapping(value = GoodsConstant.RECOMMEND, method = RequestMethod.GET)
    @ResponseBody
    public Response<List<GoodsEntity>> listRecommend() {
        Response<List<GoodsEntity>> response = new Response<>();
        response.data = mGoodsRepository.findAllByIsRecommend(new Byte("1"));

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_RECOMMEND_ERROR);
        }
        return response;
    }

    @RequestMapping(value = GoodsConstant.HOT + "/{goodsId}", method = RequestMethod.GET)
    public Response<GoodsEntity> listHotById(@PathVariable int goodsId) {
        Response<GoodsEntity> response = new Response<>();
        response.data = mGoodsRepository.findByIsHotAndGoodsId(new Byte("1"), goodsId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
    public Response<GoodsEntity> listById(@PathVariable int goodsId) {
        LOGGER.info("listOne goodsId: " + goodsId + " \n");
        Response<GoodsEntity> response = new Response<>();
        response.data = mGoodsRepository.findByGoodsId(goodsId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_GOODS_NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value = GoodsConstant.PAGINATION, method = RequestMethod.GET)
    public Page paginate(@RequestParam int catId, @RequestParam int pageSize, @RequestParam int pageIndex) {
        List<GoodsEntity> srcData = mGoodsCategoryService.listGoods(catId);
        Page page = new GoodsPaginationVO();
        PaginationUtils.paginate(page, srcData, pageSize, pageIndex);
        LOGGER.info(" paginate page: " + page);
        return page;
    }

    /**
     ** 使用InfoController中获取图片的方法
     * @param request
     * @param response
     * @param model
     * @param picName
     * @return
     */
    @Deprecated
    @RequestMapping(value = GoodsConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public String getGoodsPicture(HttpServletRequest request,
                                  HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.getPicture(response, SERVER_IMAGE_PATH, picName);
        return "getGoodsPicture success ...";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response saveGoods(GoodsAddVO goodsAddVO) {
        Response response = new Response();
        if (goodsAddVO.getImage() == null) {
            return response;
        }
        int code = mGoodsService.saveGoods(goodsAddVO);
        LOGGER.info(" saveGoods goodsVo: " + goodsAddVO);
        response.setStatusCode(code);
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response deleteGoods(@ApiParam(name = "要被删除的商品的ID", required = true) @RequestParam int goodsIds) {
        Response response = new Response();
        int code = mGoodsService.deleteGoods(goodsIds);
        response.setStatusCode(code);
        return response;
    }
}
