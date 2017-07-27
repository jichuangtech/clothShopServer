package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import com.jichuangtech.clothshopserver.utils.JsonHelper;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.IMAGE_SUFFIX;

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
        System.out.print("listOne goodsCategoryId: " + goodsCategoryId + " \n");
        JsonConfig cfg = getJsonConfig();

        GoodsCategoryEntity goodsCategory = mGoodsCategoryRepository.findOne(goodsCategoryId);
        String goodsCateJson = JsonHelper.getJson(goodsCategory, cfg);

        return goodsCateJson;
    }

//        private static final String SERVER_IMAGE_PATH = "/Users/Bingo/Desktop/shop/clothShop/img/";

    @RequestMapping(value = GoodsCategoryConstant.IMAGE + "/{picName}")
    @ResponseBody
    public String getImage(HttpServletRequest request,
                           HttpServletResponse response, Model model, @PathVariable String picName) {
        System.out.println("getImage :" + picName);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(SERVER_IMAGE_PATH + picName + IMAGE_SUFFIX);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            System.out.println(" getImage e: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (fis != null) {
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "getImage success ...";
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
