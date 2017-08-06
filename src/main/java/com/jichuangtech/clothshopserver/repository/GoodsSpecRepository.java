package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.GoodsSpecEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsSpecRepository extends JpaRepository<GoodsSpecEntity, Integer> {
}
