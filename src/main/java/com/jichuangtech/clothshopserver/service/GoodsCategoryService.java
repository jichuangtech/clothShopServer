package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.repository.GoodsCategoryRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 2017/8/22.
 */

@Service
public class GoodsCategoryService {
    private static Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryService.class.getSimpleName());
    @Autowired
    private GoodsCategoryRepository mGoodsCategoryRepository;

    public GoodsCategoryEntity getCategory(int goodsCategoryId) {
        return mGoodsCategoryRepository.findById(goodsCategoryId);
    }

    public List<GoodsEntity> listGoods(@PathVariable int goodsCategoryId) {
        GoodsCategoryEntity goodsCategory = getCategory(goodsCategoryId);
        return goodsCategory != null ? goodsCategory.getGoodsList() : new ArrayList<>();
    }

    public Response saveGoodsCategory(String name, MultipartFile image) {
        String imageName = FilenameUtils.getBaseName(image.getOriginalFilename());
        try {
            File file = new File(GoodsCategoryConstant.SERVER_IMAGE_PATH, imageName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();
        try {
            GoodsCategoryEntity entity = new GoodsCategoryEntity();
            entity.setName(name);
            entity.setImage(imageName);
            mGoodsCategoryRepository.save(entity);
        } catch (Exception e) {
            response.statusCode = -1;
            response.msg = "添加商品分类失败";
        }
        return response;
    }

    public int delete(int id) {
        int code = ResponseCode.CODE_SUCCESS;
        GoodsCategoryEntity entity = mGoodsCategoryRepository.findById(id);
        LOGGER.info(" delete id: " + id + ", entity: " + entity);

        if (entity != null) {
            mGoodsCategoryRepository.delete(entity);
        } else {
            code = ResponseCode.CODE_DELETE_NOT_FOUND;
        }

        return code;
    }


}
