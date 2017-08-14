package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	/**
	 * 根据用户id查找订单
	 * @param userId
	 * @return
	 */
	public List<OrderEntity> findByUserId(int userId);
	/**
	 * 根据订单状态查找用户订单
	 * @param orderStatus
	 * @param userId
	 * @return
	 */
	public List<OrderEntity> findByOrderStatusAndUserId(byte orderStatus,int userId);
	
}
