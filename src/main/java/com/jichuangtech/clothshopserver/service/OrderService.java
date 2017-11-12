package com.jichuangtech.clothshopserver.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mysql.jdbc.log.LogFactory;
import com.sun.org.apache.xml.internal.resolver.readers.OASISXMLCatalogReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.constant.UserConstant;
import com.jichuangtech.clothshopserver.model.GoodsEntity;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import com.jichuangtech.clothshopserver.model.OrderGoodsEntity;
import com.jichuangtech.clothshopserver.model.vo.GoodsReqVO;
import com.jichuangtech.clothshopserver.model.vo.GoodsVO;
import com.jichuangtech.clothshopserver.model.vo.OrderReqVO;
import com.jichuangtech.clothshopserver.model.vo.OrderRespVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.repository.GoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderGoodsRepository;
import com.jichuangtech.clothshopserver.repository.OrderRepository;
import com.jichuangtech.clothshopserver.repository.RegionRepository;
import com.jichuangtech.clothshopserver.utils.ListUtils;

@Service
@Transactional
public class OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class.getSimpleName());
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderGoodsRepository orderGoodsRepository;
	@Autowired
	private GoodsRepository goodsRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private UserAddressService userAddressService;


	public OrderRespVO getOrderDetail(int orderId) {
		List<OrderEntity> orders = new ArrayList<>();
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if(orderEntity == null) {
			return null;
		}
		orders.add(orderEntity);
		List<OrderRespVO> vos = getGoodsDetailInfo(orders);
		return vos != null && vos.size() > 0 ? vos.get(0) : null;
	}
	/**
	 * 查找用户所有订单
	 * @param userId
	 * @return
	 */
	public List<OrderRespVO> getList(int userId){
		List<OrderEntity> orderEntityList; 
		if(UserConstant.USER_ALL == userId){
			orderEntityList = orderRepository.findAll();
		}else{
			orderEntityList = orderRepository.findByUserId(userId);
		}
        if(orderEntityList != null && !orderEntityList.isEmpty()) {
            return getGoodsDetailInfo(orderEntityList);
        }
        return new ArrayList<OrderRespVO>();
	}
	
	/**
	 * 根据订单获取商品信息
	 * @param orderEntityList
	 * @return
	 */
	private List<OrderRespVO> getGoodsDetailInfo(List<OrderEntity> orderEntityList) {
		List<OrderRespVO> orderDetailVOList = new ArrayList<>();
		//根据订单获得所有order_goods
		List<OrderGoodsEntity> orderGoodsEntityList = getUserOrderGoodsEntityList(orderEntityList);
		//获得所有goods
		List<GoodsEntity> goodsEntityList = getUserRelGoodsEntityList(orderGoodsEntityList);
		Map<Integer, List<OrderGoodsEntity>>  orderIdListMap = ListUtils.listToListMap(Integer.class, orderGoodsEntityList, "orderId");
		Map<Integer, GoodsEntity> goodsIdEntityMap= ListUtils.listToEntityMap(Integer.class, goodsEntityList, "goodsId");
		for (int i = 0; i < orderEntityList.size(); i++) {
			OrderEntity orderEntity = orderEntityList.get(i);
			int orderId = orderEntity.getOrderId();
			List<OrderGoodsEntity>  orderIdOrderGoodsEntityList = orderIdListMap.get(orderId);
			OrderRespVO orderDetailVO = createOrderDetailVO(orderEntity,orderIdOrderGoodsEntityList,goodsIdEntityMap);
			orderDetailVOList.add(orderDetailVO);
		}
		return orderDetailVOList;
	}

	/**
	 * 根据order_goods获取goods
	 * @param orderGoodsEntityList
	 */
	private List<GoodsEntity> getUserRelGoodsEntityList(
			List<OrderGoodsEntity> orderGoodsEntityList) {
		Set<Integer> goodsIdSet = ListUtils.listToFieldSet(Integer.class, orderGoodsEntityList, "goodsId");
		List<GoodsEntity> goodsEntityList = goodsRepository.findByGoodsIdIn(goodsIdSet);
		return goodsEntityList;
	}

	/**
	 * 根据订单记录获得相关order_goods
	 * @param orderEntityList
	 */
	private List<OrderGoodsEntity> getUserOrderGoodsEntityList(List<OrderEntity> orderEntityList) {
		Set<Integer> orderIdSet = ListUtils.listToFieldSet(Integer.class,orderEntityList,"orderId");
		List<OrderGoodsEntity> orderGoodsEntityList = orderGoodsRepository.findByOrderIdIn(orderIdSet);
		return orderGoodsEntityList;
	}

	/**
	 * 获取返回对象orderDetailVO
	 * @param orderEntity
	 * @param goodsIdEntityMap 
	 * @param orderIdOrderGoodsEntityList 
	 */
	private OrderRespVO createOrderDetailVO(OrderEntity orderEntity, List<OrderGoodsEntity> orderIdOrderGoodsEntityList, Map<Integer, GoodsEntity> goodsIdEntityMap) {
		OrderRespVO orderRespVO = new OrderRespVO();
		int orderId = orderEntity.getOrderId();
		orderRespVO.setOrderId(orderId);
		
		int userId = orderEntity.getUserId();
		orderRespVO.setUserId(userId);
		
		String orderSn = orderEntity.getOrderSn();
		orderRespVO.setOrderSn(orderSn);
		
		BigDecimal totalAmount = orderEntity.getTotalAmount();
		orderRespVO.setTotalAmount(totalAmount);
		
		String address = orderEntity.getAddress();
		orderRespVO.setAddress(address);
		orderRespVO.setAddressId(orderEntity.getAddressId());
		
		String mobile = orderEntity.getMobile();
		orderRespVO.setMobile(mobile);
		
		String consignee = orderEntity.getConsignee();
		orderRespVO.setConsignee(consignee);
		
		byte orderStatus = orderEntity.getOrderStatus();
		orderRespVO.setOrderStatus(orderStatus);
		
		List<GoodsVO> goodsVOList = getGoodsVO(orderIdOrderGoodsEntityList,goodsIdEntityMap);
		orderRespVO.setGoodsVO(goodsVOList);
		return orderRespVO;
	}

	/**
	 * 获取该订单的商品信息
	 * @param orderIdOrderGoodsEntityList 该用户的order_goods记录
	 * @param goodsIdEntityMap
	 * @return
	 */
	private List<GoodsVO> getGoodsVO(List<OrderGoodsEntity> orderIdOrderGoodsEntityList, Map<Integer, GoodsEntity> goodsIdEntityMap) {
		List<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		for (int j = 0; j < orderIdOrderGoodsEntityList.size(); j++) {
			GoodsVO goodsVO = new GoodsVO();
			OrderGoodsEntity orderGoodsEntity = orderIdOrderGoodsEntityList.get(j);
			
			short goodsNum = orderGoodsEntity.getGoodsNum();
			goodsVO.setGoodsNum(goodsNum);
			
			int goodsId = orderGoodsEntity.getGoodsId();
			goodsVO.setGoodsId(goodsId);
			//根据商品id查找商品信息
			GoodsEntity goodsEntity = goodsIdEntityMap.get(goodsId);
			String goodsName = goodsEntity.getGoodsName();
			goodsVO.setGoodsName(goodsName);
			
			String originalImg = goodsEntity.getOriginalImg();
			goodsVO.setOriginalImg(originalImg);
			
			BigDecimal shopPrice = goodsEntity.getShopPrice();
			goodsVO.setShopPrice(shopPrice);
			
			String goodsSn = goodsEntity.getGoodsSn();
			goodsVO.setGoodsSn(goodsSn);
			
			//规格
			String specKey = orderGoodsEntity.getSpecKey();
			String specName = goodsEntity.getGoodsSpec(Integer.valueOf(specKey)).getSpecName();
            goodsVO.setSpecName(specName);
            
            //颜色
            int colorId = orderGoodsEntity.getColorId();
            String colorName = goodsEntity.getGoodsColor(colorId).getColorName();
            goodsVO.setColor(colorName);
			
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
	public List<OrderRespVO> getByOrderStatus(byte orderStatus,int userId){
		if(OrderConstant.ORDER_USER_ALL == orderStatus){
			return getList(userId);
		}
		List<OrderEntity> orderEntityList = orderRepository.findByOrderStatusAndUserId(orderStatus, userId);
		if(orderEntityList != null && !orderEntityList.isEmpty()) {
			return getGoodsDetailInfo(orderEntityList);
		} else {
			return new ArrayList<>();
		}

	}
	
	/**
	 * 保存订单
	 * @param userId
	 */
	public OrderRespVO saveOrder(int userId,OrderReqVO orderReqVO){
		OrderRespVO orderRespVO = new OrderRespVO();
		List<GoodsReqVO> goodsReqVOList = orderReqVO.getGoodsReqVOList();
		
		//写入订单表
		OrderEntity orderEntity = createOrder(userId,orderReqVO);
		int orderId = orderEntity.getOrderId();
		//写入订单商品表
		createOrderGoods(orderId,goodsReqVOList);
		orderRespVO.setOrderId(orderId);
		orderRespVO.setTotalAmount(orderEntity.getTotalAmount());
		return orderRespVO;
	}

	/**
	 * 计算订单总价
	 * @param orderReqVO
	 */
	private double getTotalAmount(OrderReqVO orderReqVO) {
		List<GoodsReqVO> goodsReqVOList = orderReqVO.getGoodsReqVOList();
		//获取商品信息
		List<GoodsEntity> goodsEntityList = getGoodsInfo(goodsReqVOList);
		double totalAmount = 0.00;
		Map<Integer, GoodsEntity>  goodsIdEntityMap = ListUtils.listToEntityMap(Integer.class, goodsEntityList, "goodsId");
		for (int i = 0; i < goodsReqVOList.size(); i++) {
			GoodsReqVO goodsReqVO = goodsReqVOList.get(i);
			int goodsId = goodsReqVO.getGoodsId();
			GoodsEntity goodsEntity = goodsIdEntityMap.get(goodsId);
			int specId = goodsReqVO.getSpecId();
			double specPrice = goodsEntity.getGoodsSpec(specId).getSpecPrice();
			short num = goodsReqVO.getGoodsNum();
			totalAmount += specPrice * num;
		}
		return totalAmount;
	}

	/**
	 * 根据请求商品id获取商品信息
	 * @param orderRespVO
	 */
	private List<GoodsEntity> getGoodsInfo(List<GoodsReqVO> goodsReqVOList) {
		Set<Integer> goodsIdSet = ListUtils.listToFieldSet(Integer.class, goodsReqVOList, "goodsId");
		List<GoodsEntity> goodsEntityList = goodsRepository.findByGoodsIdIn(goodsIdSet);
		return goodsEntityList;
	}

	/**
	 * 生成订单商品
	 * @param orderId
	 * @param goodsVOList
	 */
	private List<OrderGoodsEntity> createOrderGoods(int orderId, List<GoodsReqVO> goodsReqVOList) {
		List<OrderGoodsEntity> orderGoodsEntityList = new ArrayList<OrderGoodsEntity>();
		for (int i = 0; i < goodsReqVOList.size(); i++) {
			GoodsReqVO goodsReqVO = goodsReqVOList.get(i);
			OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
			orderGoodsEntity.setOrderId(orderId);
			orderGoodsEntity.setGoodsId(goodsReqVO.getGoodsId());
			
			orderGoodsEntity.setGoodsNum(goodsReqVO.getGoodsNum());
            orderGoodsEntity.setSpecKey(String.valueOf(goodsReqVO.getSpecId()));
            orderGoodsEntity.setColorId(goodsReqVO.getColorId());
			orderGoodsEntityList.add(orderGoodsEntity);
		}
		return orderGoodsRepository.save(orderGoodsEntityList);
	}

	/**
	 * 生成订单
	 * @param
	 */
	private OrderEntity createOrder(int userId,OrderReqVO orderReqVO) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderSn = dateFormat.format(date);
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderSn(orderSn);
		orderEntity.setUserId(userId);
		orderEntity.setAddressId(orderReqVO.getAddressId());
		orderEntity.setOrderStatus(OrderConstant.ORDER_UN_PAID);
		UserAddressRespVO  userAddress = userAddressService.getAddressByAddressId(orderReqVO.getAddressId());
		orderEntity.setAddress(userAddress.getAddress());
		orderEntity.setConsignee(userAddress.getConsignee());
		//计算总价
		double totalAmount = getTotalAmount(orderReqVO);
		orderEntity.setTotalAmount(new BigDecimal(totalAmount));
		orderEntity.setMobile(userAddress.getMobile());
		return orderRepository.save(orderEntity);
	}
	
	/**
	 * 根据用户id修改订单状态
	 * @param userId
	 * @param orderStatus
	 * @return
	 */
	public void updateOrderStatusByOrderId(int userId,int orderId,byte orderStatus){
		orderRepository.updateOrderStatusByOrderId(orderStatus, orderId);
	}
}
