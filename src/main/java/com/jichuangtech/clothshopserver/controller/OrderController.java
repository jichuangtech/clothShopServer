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
import io.swagger.annotations.ApiOperation;

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
    @ApiOperation(value = "获取用户所有订单信息")
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
    @ApiOperation(value = "根据订单状态查找用户订单", notes = "订单状态为0表示所有订单")
    @RequestMapping(value = "/{userId}/{orderStatus}", method = RequestMethod.GET)
    public Response<List<OrderRespVO>> getByOrderStatus(@PathVariable("orderStatus") byte orderStatus, @PathVariable("userId") int userId) {
        Response<List<OrderRespVO>> response = new Response<List<OrderRespVO>>();
        response.data = orderService.getByOrderStatus(orderStatus, userId);
        return response;
    }

    /**
     * 保存订单
     * @param userId
     * @param orderDetailVO
     * @return
     */
    @ApiOperation(value = "保存用户订单")
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public Response<OrderRespVO> saveOrder(@PathVariable("userId") int userId, @RequestBody OrderReqVO orderReqVO) {
        Response<OrderRespVO> response = new Response<OrderRespVO>();
        response.data = orderService.saveOrder(userId, orderReqVO);
        return response;
    }
    
    /**
     * 修改订单状态
     * @return
     */
    @ApiOperation(value = "更新用户某个订单的订单状态")
    @RequestMapping(value = "/{userId}/orderstatus/{orderId}/{orderStatus}", method = RequestMethod.POST)
    public Response<String> updateOrderStatus(@PathVariable("userId") int userId,@PathVariable("orderId") int orderId,@PathVariable("orderStatus") byte orderStatus){
    	orderService.updateOrderStatusByOrderId(userId,orderId,orderStatus);
    	Response<String> response = new Response<String>();
    	return response;
    }
}
