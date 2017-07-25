package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.utils.JsonHelper;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
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
@RequestMapping(GoodsCategoryConstant.API_GOODS_CATEGORY)
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "goodsCategory";
    }

    @RequestMapping(value = GoodsCategoryConstant.LIST, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String list() {

        JsonConfig cfg = getJsonConfig();

        List<GoodsCategoryEntity> goodsCatesList = mGoodsCategoryRepository.findAll();
        String goodsCatesJson = JsonHelper.getJson(goodsCatesList, cfg);


        System.out.println(" list GoodsCategoryEntity.size1: " + goodsCatesList.size() + ", entitys: " + goodsCatesJson);
        return goodsCatesJson;
    }

    @RequestMapping(value = GoodsCategoryConstant.LIST + "/{goodsCategoryId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listOne(@PathVariable int goodsCategoryId) {
        System.out.print("listOne goodsCategoryId: " + goodsCategoryId +" \n");
        JsonConfig cfg = getJsonConfig();

        GoodsCategoryEntity goodsCategory = mGoodsCategoryRepository.findOne(goodsCategoryId);
        String goodsCateJson = JsonHelper.getJson(goodsCategory, cfg);

        return goodsCateJson;
    }

    private JsonConfig getJsonConfig() {
        JsonConfig cfg = new JsonConfig();
        cfg.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (name.equals("categoryId")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        return cfg;
    }

}
