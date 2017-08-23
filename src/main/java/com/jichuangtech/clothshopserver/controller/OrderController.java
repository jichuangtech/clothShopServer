package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.OrderReqVO;
import com.jichuangtech.clothshopserver.model.vo.OrderRespVO;
import com.jichuangtech.clothshopserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.List;

@Api(description = "订单模块接口")
@RestController
@RequestMapping(OrderConstant.API_ORDER)
@Transactional
public class OrderController {
    @Autowired
    private OrderService orderService;


    /**
     * 查找用户所有订单
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<OrderRespVO>> list(@PathVariable("userId") int userId) {
        Response<List<OrderRespVO>> response = new Response<List<OrderRespVO>>();
        response.data = orderService.getList(userId);
        return response;
    }


    /**
     * 根据订单状态查找用户订单
     *
     * @param orderStatus
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/{orderStatus}", method = RequestMethod.GET)
    public Response<List<OrderRespVO>> getByOrderStatus(@PathVariable("orderStatus") byte orderStatus, @PathVariable("userId") int userId) {
        Response<List<OrderRespVO>> response = new Response<List<OrderRespVO>>();
        response.data = orderService.getByOrderStatus(orderStatus, userId);
        return response;
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    /**
     * 保存订单
     * @param userId
     * @param orderDetailVO
     * @return
     */
    public Response<OrderRespVO> saveOrder(@PathVariable("userId") int userId, @RequestBody OrderReqVO orderReqVO) {
        Response<OrderRespVO> response = new Response<OrderRespVO>();
        response.data = orderService.saveOrder(userId, orderReqVO);
        return response;
    }
    
    @RequestMapping(value = "/{userId}/orderstatus/{orderId}/{orderStatus}", method = RequestMethod.POST)
    /**
     * 修改订单状态
     * @return
     */
    public Response<String> updateOrderStatus(@PathVariable("userId") int userId,@PathVariable("orderId") int orderId,@PathVariable("orderStatus") byte orderStatus){
    	orderService.updateOrderStatusByOrderId(userId,orderId,orderStatus);
    	Response<String> response = new Response<String>();
    	return response;
    }
}
