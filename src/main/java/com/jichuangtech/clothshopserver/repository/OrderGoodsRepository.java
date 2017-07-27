package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;

public interface OrderGoodsRepository extends JpaRepository<OrderGoodsEntity, Integer> {
	
	public List<OrderGoodsEntity> findByOrderId(int orderId);
}
