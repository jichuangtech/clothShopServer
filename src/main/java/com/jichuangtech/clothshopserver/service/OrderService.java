package com.jichuangtech.clothshopserver.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;
import com.jichuangtech.clothshopserver.model.vo.GoodsVO;
import com.jichuangtech.clothshopserver.model.vo.OrderDetailVO;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderGoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderGoodsRepository orderGoodsRepository;
	@Autowired
	private GoodsRepository goodsRepository;
	
	/**
	 * 查找用户所有订单
	 * @return
	 */
	public List<OrderDetailVO> getList(int userId){
		List<OrderEntity> orderEntityList = orderRepository.findByUserId(userId);
		return getGoodsDetailInfo(orderEntityList);
//		List<OrderGoodsEntity> orderGoodsEntityList = orderGoodsRepository.findAll();
//		System.out.println(orderGoodsEntityList);
//		return null;
	}
	
	/**
	 * 根据订单获取商品信息
	 * @param orderEntityList
	 * @return
	 */
	private List<OrderDetailVO> getGoodsDetailInfo(List<OrderEntity> orderEntityList) {
		List<OrderDetailVO> orderDetailVOList = new ArrayList<OrderDetailVO>();
		for (int i = 0; i < orderEntityList.size(); i++) {
			OrderEntity orderEntity = orderEntityList.get(i);
			int orderId = orderEntity.getOrderId();
			List<GoodsVO> goodsVOList = getGoodsVO(orderId);
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			BigDecimal totalAmount = orderEntity.getTotalAmount();
			orderDetailVO.setTotalAmount(totalAmount);
			orderDetailVO.setGoodsVO(goodsVOList);
			orderDetailVOList.add(orderDetailVO);
		}
		return orderDetailVOList;
	}

	private List<GoodsVO> getGoodsVO(int orderId) {
		List<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		//根据orderId查找order_goods表
		List<OrderGoodsEntity> orderGoodsEntityList = orderGoodsRepository.findByOrderId(orderId);
		for (int j = 0; j < orderGoodsEntityList.size(); j++) {
			GoodsVO goodsVO = new GoodsVO();
			OrderGoodsEntity orderGoodsEntity = orderGoodsEntityList.get(j);
			short goodsNum = orderGoodsEntity.getGoodsNum();
			BigDecimal goodsPrice = orderGoodsEntity.getGoodsPrice();
			goodsVO.setGoodsNum(goodsNum);
			goodsVO.setGoodsPrice(goodsPrice);
			int goodsId = orderGoodsEntity.getGoodsId();
			//根据商品id查找商品信息
			GoodsEntity goodsEntity = goodsRepository.findByGoodsId(goodsId);
			String goodsName = goodsEntity.getGoodsName();
			goodsVO.setGoodsName(goodsName);
			goodsVOList.add(goodsVO);
		}
		return goodsVOList;
	}

	/**
	 * 根据订单状态查找用户订单
	 * @param orderStatus
	 * @param userId
	 * @return
	 */
	public List<OrderDetailVO> getByOrderStatus(byte orderStatus,int userId){
		List<OrderEntity> orderEntityList = orderRepository.findByOrderStatusAndUserId(orderStatus, userId);
		return getGoodsDetailInfo(orderEntityList);
	}
	
	/**
	 * 根据发货状态查找用户订单
	 * @param shippingStatus
	 * @param userId
	 * @return
	 */
	public List<OrderDetailVO> getByShippingStatus(byte shippingStatus,int userId){
		List<OrderEntity> orderEntityList = orderRepository.findByShippingStatusAndUserId(shippingStatus, userId);
		return getGoodsDetailInfo(orderEntityList);
	}
	
	/**
	 * 根据支付状态查找用户订单
	 * @param payStatus
	 * @param userId
	 * @return
	 */
	public List<OrderDetailVO> getByPayStatus(byte payStatus,int userId){
		List<OrderEntity> orderEntityList = orderRepository.findByPayStatusAndUserId(payStatus, userId);
		return getGoodsDetailInfo(orderEntityList);
	}
	
	/**
	 * 保存订单
	 * @param orderEntity
	 */
	public void saveOrder(OrderEntity orderEntity){
		orderRepository.saveAndFlush(orderEntity);
	}
}
