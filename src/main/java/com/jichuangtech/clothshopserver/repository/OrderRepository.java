package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
	
	@Modifying 
	@Query("update OrderEntity set order_status = ?1 where order_id = ?2") 
	/**
	 * 根据订单id修改订单状态
	 * @param isDefault
	 * @param addressId
	 * @return
	 */
	int updateOrderStatusByOrderId(int orderStatus, int orderId); 
	
}
