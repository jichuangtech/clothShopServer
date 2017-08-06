package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.GoodsConstant;
import com.jichuangtech.clothshopserver.constant.GoodsSpecConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsSpecEntity;
import com.jichuangtech.clothshopserver.repository.GoodsSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Bingo on 2017/7/21.
 */

@Controller
@RequestMapping(GoodsSpecConstant.API_GOODS_SPEC)
public class GoodsSpecController {

    @Autowired
    private GoodsSpecRepository mGoodsSpecRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println("进行规格的访问");
        return "goodsSpec";
    }

    @RequestMapping()
    @ResponseBody
    public List<GoodsSpecEntity> list() {
        return mGoodsSpecRepository.findAll();
    }

    @RequestMapping("/{goodsSpecId}")
    @ResponseBody
    public GoodsSpecEntity listById(@PathVariable int goodsSpecId) {
        System.out.print("listOne goodsSpecId: " + goodsSpecId + " \n");
        return mGoodsSpecRepository.findOne(goodsSpecId);
    }
}
