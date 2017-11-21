package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.InfoConstant;
import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.repository.ColorRepository;
import com.jichuangtech.clothshopserver.service.GoodsInfoService;
import com.jichuangtech.clothshopserver.utils.PictureUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * 用来请求一些公开的数据，不需要进行身份验证例如：
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
    private ColorRepository mColorRepository;

    @RequestMapping(InfoConstant.COLOR)
    public List<ColorEntity> getColors () {
        return mColorRepository.findAll();
    }

    @RequestMapping(value = InfoConstant.PICTURE + "/{picName}", method = RequestMethod.GET)
    public Response getPicture(HttpServletResponse response, @PathVariable String picName) {
        Response res = new Response();
        res.setStatusCode(PictureUtils.getPicture(response, SERVER_IMAGE_PATH, picName));
        return res;
    }

}
