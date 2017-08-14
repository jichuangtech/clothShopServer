package com.jichuangtech.clothshopserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import com.jichuangtech.clothshopserver.model.vo.OrderDetailVO;
import com.jichuangtech.clothshopserver.service.OrderService;

@Controller
@RequestMapping(OrderConstant.API_ORDER)
@Transactional
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/{userId}",method = RequestMethod.GET)
	@ResponseBody
	/**
	 * 查找用户所有订单
	 * @param userId
	 * @return
	 */
	public List<OrderDetailVO> list(@PathVariable("userId")int userId){
		return orderService.getList(userId);
	}
	
	@RequestMapping(value="/{userId}/{orderStatus}",method = RequestMethod.GET)
	@ResponseBody
	/**
	 * 根据订单状态查找用户订单
	 * @param orderStatus
	 * @param userId
	 * @return
	 */
	public List<OrderDetailVO> getByOrderStatus(@PathVariable("orderStatus")byte orderStatus,@PathVariable("userId")int userId){
		return orderService.getByOrderStatus(orderStatus, userId);
	}
	

	@RequestMapping(value="/{userId}",method=RequestMethod.POST)
	@ResponseBody
	/**
	 * 保存订单
	 * @param userId
	 * @param orderDetailVO
	 * @return
	 */
	public OrderDetailVO saveOrder(@PathVariable("userId")int userId,@RequestBody OrderDetailVO orderDetailVO){
		return orderService.saveOrder(userId,orderDetailVO);
	}
}
