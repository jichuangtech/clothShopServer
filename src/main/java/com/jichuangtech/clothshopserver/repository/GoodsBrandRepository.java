package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsBrandEntity;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsBrandRepository extends JpaRepository<GoodsBrandEntity, Integer> {
	GoodsBrandEntity findById(int goodsBrandId);
}
