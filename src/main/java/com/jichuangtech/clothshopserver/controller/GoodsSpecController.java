package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsSpecConstant;
import com.jichuangtech.clothshopserver.model.GoodsSpecEntity;
import com.jichuangtech.clothshopserver.repository.GoodsSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bingo on 2017/7/21.
 */

@RestController
@RequestMapping(GoodsSpecConstant.API_GOODS_SPEC)
public class GoodsSpecController {

    @Autowired
    private GoodsSpecRepository mGoodsSpecRepository;

    @RequestMapping()
    public List<GoodsSpecEntity> list() {
        return mGoodsSpecRepository.findAll();
    }

    @RequestMapping("/{goodsSpecId}")
    public GoodsSpecEntity listById(@PathVariable int goodsSpecId) {
        System.out.print("listOne goodsSpecId: " + goodsSpecId + " \n");
        return mGoodsSpecRepository.findOne(goodsSpecId);
    }
}
