package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsInfoConstant;
import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.ColorRepository;
import com.jichuangtech.clothshopserver.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bingo on 2017/9/9.
 */


@RestController
@RequestMapping(GoodsInfoConstant.API_GOODS_INFO)
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService mService;
    @Autowired
    private ColorRepository mColorRepository;

    @RequestMapping(GoodsInfoConstant.COLOR)
    public List<ColorEntity> getColors () {
        return mColorRepository.findAll();
    }
}
