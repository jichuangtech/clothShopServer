package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bingo on 2017/9/9.
 */

@Service
public class GoodsInfoService {

    @Autowired
    private ColorRepository mColorRepository;


}
