package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
