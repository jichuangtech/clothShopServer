package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsConstant;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<GoodsEntity> list() {
        return mGoodsRepository.findAll();
    }

    @RequestMapping(value = GoodsConstant.HOT, method = RequestMethod.GET)
    public List<GoodsEntity> listHot() {
        return mGoodsRepository.findAllByIsHot(new Byte("1"));
    }

    @RequestMapping(value = GoodsConstant.RECOMMEND, method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsEntity> listRecommend() {
        return mGoodsRepository.findAllByIsRecommend(new Byte("1"));
    }

    @RequestMapping(value = GoodsConstant.HOT + "/{goodsId}", method = RequestMethod.GET)
    public GoodsEntity listHotById(@PathVariable int goodsId) {
        return mGoodsRepository.findByIsHotAndGoodsId(new Byte("1"), goodsId);
    }

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
    public GoodsEntity listById(@PathVariable int goodsId) {
        System.out.print("listOne goodsId: " + goodsId + " \n");
        return mGoodsRepository.findByGoodsId(goodsId);
    }

    @RequestMapping(value = GoodsConstant.PAGINATION, method = RequestMethod.GET)
    public Page paginate(@RequestParam int catId, @RequestParam int pageSize, @RequestParam int pageIndex) {
        List<GoodsEntity> srcData = mGoodsCategoryService.listGoods(catId);
        Page page = new GoodsPaginationVO();
        PaginationUtils.paginate(page, srcData, pageSize, pageIndex);
        LOGGER.info(" paginate page: " + page);
        return page;
    }


    @RequestMapping(value = GoodsConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public String getGoodsPicture(HttpServletRequest request,
                                  HttpServletResponse response, Model model, @PathVariable String picName) {
        PictureUtils.writePic(response, SERVER_IMAGE_PATH, picName, IMAGE_SUFFIX);
        return "getGoodsPicture success ...";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response saveGoods(GoodsAddVO goodsAddVO) {
        Response response = new Response();
        int code = mGoodsService.saveGoods(goodsAddVO);
        LOGGER.info(" saveGoods goodsVo: " + goodsAddVO);
        response.setStatusCode(code);
        return response;
    }
}
