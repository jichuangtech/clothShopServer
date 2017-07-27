package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.GoodsEntity;


public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
	public GoodsEntity findByGoodsId(int goodsId);
}
