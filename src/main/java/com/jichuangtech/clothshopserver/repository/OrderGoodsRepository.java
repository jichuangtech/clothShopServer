package com.jichuangtech.clothshopserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;

public interface OrderGoodsRepository extends JpaRepository<OrderGoodsEntity, Integer> {
	/**
	 * 根据orderId获取结果集
	 * @param orderId
	 * @return
	 */
	public List<OrderGoodsEntity> findByOrderId(int orderId);
	/**
	 * 根据orderId集合获取结果集
	 * @param orderIdSet
	 * @return
	 */
	public List<OrderGoodsEntity> findByOrderIdIn(Set<Integer> orderIdSet);
}
