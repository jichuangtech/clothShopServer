package com.jichuangtech.clothshopserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/{userId}",method = RequestMethod.GET)
	@ResponseBody
	public List<OrderDetailVO> list(@PathVariable("userId")int userId){
		return orderService.getList(userId);
	}
	
	@RequestMapping(value="/{userId}/{orderStatus}",method = RequestMethod.GET)
	@ResponseBody
	public List<OrderDetailVO> getByOrderStatus(@PathVariable("orderStatus")byte orderStatus,@PathVariable("userId")int userId){
		return orderService.getByOrderStatus(orderStatus, userId);
	}
	
	@RequestMapping(OrderConstant.SHIPPING_STATUS_LIST)
	@ResponseBody
	public List<OrderDetailVO> getByShippingStatus(byte shippingStatus,int userId){
		return orderService.getByShippingStatus(shippingStatus, userId);
	}
	
	@RequestMapping(OrderConstant.PAY_STATUS_LIST)
	@ResponseBody
	public List<OrderDetailVO> getByPayStatus(byte payStatus,int userId){
		return orderService.getByPayStatus(payStatus, userId);
	}

	@RequestMapping(value="/{userId}",method=RequestMethod.POST)
	@ResponseBody
	public OrderDetailVO saveOrder(@PathVariable("userId")int userId,@RequestBody OrderDetailVO orderDetailVO){
		return orderService.saveOrder(userId,orderDetailVO);
	}
}
