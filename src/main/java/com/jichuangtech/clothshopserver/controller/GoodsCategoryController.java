package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.utils.JsonHelper;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

        List<GoodsCategoryEntity> entityList = mGoodsCategoryRepository.findAll();
        String entitys = JsonHelper.getJson(entityList, cfg);


//        String entitys = "";
        System.out.println(" list GoodsCategoryEntity.size1: " + entityList.size() + ", entitys: " + entitys);
        return entitys;
    }
}
