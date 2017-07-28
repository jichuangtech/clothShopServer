package com.jichuangtech.clothshopserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(OrderConstant.LIST)
	@ResponseBody
	public List<OrderDetailVO> list(int userId){
		System.out.print("我正在查询");
		return orderService.getList(userId);
	}
	
	@RequestMapping(OrderConstant.ORDER_STATUS_LIST)
	@ResponseBody
	public List<OrderDetailVO> getByOrderStatus(byte orderStatus,int userId){
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

	
	public void saveOrder(OrderEntity orderEntity){
		orderService.saveOrder(orderEntity);
	}
}
