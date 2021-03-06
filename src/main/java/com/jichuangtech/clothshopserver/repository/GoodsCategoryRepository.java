package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategoryEntity, Integer> {
    GoodsCategoryEntity findById(int goodsCategoriyId);
}
