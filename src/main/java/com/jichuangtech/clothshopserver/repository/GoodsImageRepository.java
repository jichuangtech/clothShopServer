package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsColorEntity;
import com.jichuangtech.clothshopserver.model.GoodsImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsImageRepository extends JpaRepository<GoodsImagesEntity, Long>{

}
