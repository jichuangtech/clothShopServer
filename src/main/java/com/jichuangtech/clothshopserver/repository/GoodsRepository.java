package com.jichuangtech.clothshopserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.GoodsEntity;

/**
 * Created by Bingo on 2017/7/23.
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
	public GoodsEntity findByGoodsId(int goodsId);
	public GoodsEntity findByIsHotAndGoodsId(Byte isHot, int goodsId);
	List<GoodsEntity> findAllByIsHot(Byte isHot);
	List<GoodsEntity> findAllByIsRecommend(Byte isRecommend);
	List<GoodsEntity> findByGoodsIdIn(Set<Integer> goodsIdSet);
}
