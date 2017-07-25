package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
}
