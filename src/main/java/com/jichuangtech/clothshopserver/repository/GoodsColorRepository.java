package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.GoodsColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsColorRepository extends JpaRepository<GoodsColorEntity, Long>{

}
