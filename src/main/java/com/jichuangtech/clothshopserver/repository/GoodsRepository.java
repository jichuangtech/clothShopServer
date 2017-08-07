package com.jichuangtech.clothshopserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jichuangtech.clothshopserver.model.GoodsEntity;

import java.util.List;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
	public GoodsEntity findByGoodsId(int goodsId);
	public GoodsEntity findByIsHotAndGoodsId(Byte isHot, int goodsId);
	List<GoodsEntity> findAllByIsHot(Byte isHot);
}
