package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.Constant;
import com.jichuangtech.clothshopserver.constant.OrderConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.OrderReqVO;
import com.jichuangtech.clothshopserver.model.vo.OrderRespVO;
import com.jichuangtech.clothshopserver.service.OrderService;
import com.jichuangtech.clothshopserver.service.SessionService;
import com.jichuangtech.clothshopserver.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Collections;
import java.util.List;

@Api(description = "订单模块接口")
@RestController
@RequestMapping(OrderConstant.API_ORDER)
@Transactional
public class OrderController {
    private static final String TAG = OrderController.class.getSimpleName();
    private static Logger LOGGER = LoggerFactory.getLogger(Constant.MODULE_NAME);
    @Autowired
    private OrderService orderService;

    @Autowired
    private UsersService usersService;
    /**
     * 查找用户所有订单
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户所有订单信息")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<OrderRespVO>> list(@PathVariable("userId") int userId,
                                            @RequestHeader(value = "access_token") String accessToken,
                                            @RequestHeader(value = "is_from_cms", defaultValue = "false", required = false)
                                            boolean isFromCMS) {
        if(!isFromCMS) {
            userId = usersService.getUserIdByToken(accessToken);
        }
        Response<List<OrderRespVO>> response = new Response<List<OrderRespVO>>();
        response.data = orderService.getList(userId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ORDER_GET_ERROR);
        } else {
            Collections.reverse(response.data);
        }
        return response;
    }

    @ApiOperation(value = "获取用户所有订单信息")
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    public Response<OrderRespVO> getOrderDetail(@PathVariable("orderId") int orderId) {
        Response<OrderRespVO> response = new Response<>();
        response.data = orderService.getOrderDetail(orderId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ORDER_DETAIL_GET_ERROR);
        }
        LOGGER.info(" getOrderDetail response: " + response);
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
    public Response<List<OrderRespVO>> getByOrderStatus(@PathVariable("orderStatus") byte orderStatus,
                                                        @PathVariable("userId") int userId,
                                                        @RequestHeader("access_token") String accessToken) {

        LOGGER.info("getByOrderStatus userId: " + userId
                + ", orderStatus: " + orderStatus
                + ", accessToken: " + accessToken);
        Response<List<OrderRespVO>> response = new Response<>();
        userId = usersService.getUserIdByToken(accessToken);
        response.data = orderService.getByOrderStatus(orderStatus, userId);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ORDER_GET_ERROR);
        } else {
            Collections.reverse(response.data);
        }
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
    public Response<OrderRespVO> saveOrder(@PathVariable("userId") int userId,
                                           @RequestBody OrderReqVO orderReqVO,
                                           @RequestHeader("access_token") String accessToken) {
        Response<OrderRespVO> response = new Response<>();
        userId = usersService.getUserIdByToken(accessToken);
        orderReqVO.setUserId(userId);
        response.data = orderService.saveOrder(userId, orderReqVO);

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ORDER_CREATE_ERROR);
        }
        return response;
    }
    
    /**
     * 修改订单状态
     * @return
     */
    @ApiOperation(value = "更新用户某个订单的订单状态")
    @RequestMapping(value = "/{userId}/orderstatus/{orderId}/{orderStatus}", method = RequestMethod.POST)
    public Response<String> updateOrderStatus(@PathVariable("userId") int userId,
                                              @PathVariable("orderId") int orderId,
                                              @PathVariable("orderStatus") byte orderStatus,
                                              @RequestHeader("access_token") String accessToken){
        LOGGER.info("updateOrderStatus orderId: " + orderId + ", orderStatus: " + orderStatus);
        userId = usersService.getUserIdByToken(accessToken);
    	orderService.updateOrderStatusByOrderId(userId,orderId,orderStatus);
    	Response<String> response = new Response<String>();
    	return response;
    }

}
