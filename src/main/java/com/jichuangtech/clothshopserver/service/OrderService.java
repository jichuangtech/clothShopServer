package com.jichuangtech.clothshopserver.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;
import com.jichuangtech.clothshopserver.model.vo.GoodsVO;
import com.jichuangtech.clothshopserver.model.vo.OrderDetailVO;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderGoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderRepository;

@Service
@Transactional
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
			OrderDetailVO orderDetailVO = createOrderDetailVO(orderId,orderEntity);
			orderDetailVOList.add(orderDetailVO);
		}
		return orderDetailVOList;
	}

	/**
	 * 获取orderDetailVO
	 * @param orderEntity
	 */
	private OrderDetailVO createOrderDetailVO(int orderId,OrderEntity orderEntity) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		BigDecimal totalAmount = orderEntity.getTotalAmount();
		String address = orderEntity.getAddress();
		String mobile = orderEntity.getMobile();
		String consignee = orderEntity.getConsignee();
		String orderSn = orderEntity.getOrderSn();
		byte orderStatus = orderEntity.getOrderStatus();
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setOrderSn(orderSn);
		orderDetailVO.setTotalAmount(totalAmount);
		orderDetailVO.setAddress(address);
		orderDetailVO.setMobile(mobile);
		orderDetailVO.setConsignee(consignee);
		orderDetailVO.setOrderStatus(orderStatus);
		List<GoodsVO> goodsVOList = getGoodsVO(orderId);
		orderDetailVO.setGoodsVO(goodsVOList);
		return orderDetailVO;
	}

	/**
	 * 获取该订单的商品信息
	 * @param orderId
	 * @return
	 */
	private List<GoodsVO> getGoodsVO(int orderId) {
		List<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		//根据orderId查找order_goods表
		List<OrderGoodsEntity> orderGoodsEntityList = orderGoodsRepository.findByOrderId(orderId);
		for (int j = 0; j < orderGoodsEntityList.size(); j++) {
			GoodsVO goodsVO = new GoodsVO();
			OrderGoodsEntity orderGoodsEntity = orderGoodsEntityList.get(j);
			short goodsNum = orderGoodsEntity.getGoodsNum();
			double goodsPrice = orderGoodsEntity.getGoodsPrice();
			goodsVO.setGoodsNum(goodsNum);
			goodsVO.setGoodsPrice(goodsPrice);
            goodsVO.setSpecName(orderGoodsEntity.getSpecName());
            goodsVO.setGoodsSn(orderGoodsEntity.getGoodsSn());
			int goodsId = orderGoodsEntity.getGoodsId();
			goodsVO.setGoodsId(goodsId);
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
		if(orderStatus == 0){
			return getList(userId);
		}
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
	public OrderDetailVO saveOrder(int userId,OrderDetailVO orderDetailVO){
		List<GoodsVO> goodsVOList = orderDetailVO.getGoodsVO();
		//写入订单表
		OrderEntity orderEntity = createOrder(userId,orderDetailVO);
		int orderId = orderEntity.getOrderId();
		//写入订单商品表
		createOrderGoods(orderId,goodsVOList);
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setOrderSn(orderEntity.getOrderSn());
		orderDetailVO.setOrderStatus(OrderConstant.ORDER_UN_PAID);
		return orderDetailVO;
	}

	/**
	 * 生成订单商品
	 * @param orderId
	 * @param goodsVOList
	 */
	private List<OrderGoodsEntity> createOrderGoods(int orderId, List<GoodsVO> goodsVOList) {
		List<OrderGoodsEntity> orderGoodsEntityList = new ArrayList<OrderGoodsEntity>();
		for (int i = 0; i < goodsVOList.size(); i++) {
			GoodsVO goodsVO = goodsVOList.get(i);
			OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
			orderGoodsEntity.setOrderId(orderId);
			orderGoodsEntity.setGoodsId(goodsVO.getGoodsId());
			orderGoodsEntity.setGoodsNum(goodsVO.getGoodsNum());
			orderGoodsEntity.setGoodsPrice(goodsVO.getGoodsPrice());
            orderGoodsEntity.setGoodsSn(goodsVO.getGoodsSn());
            orderGoodsEntity.setSpecName(goodsVO.getSpecName());
			orderGoodsEntityList.add(orderGoodsEntity);
		}
		return orderGoodsRepository.save(orderGoodsEntityList);
	}

	/**
	 * 生成订单
	 * @param orderDetailVO
	 */
	private OrderEntity createOrder(int userId,OrderDetailVO orderDetailVO) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderSn = dateFormat.format(date);
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderSn(orderSn);
		orderEntity.setUserId(userId);
		orderEntity.setOrderStatus(OrderConstant.ORDER_UN_PAID);
		orderEntity.setAddress(orderDetailVO.getAddress());
		orderEntity.setConsignee(orderDetailVO.getConsignee());
		orderEntity.setTotalAmount(orderDetailVO.getTotalAmount());
		orderEntity.setMobile(orderDetailVO.getMobile());
		return orderRepository.save(orderEntity);
	}
}
