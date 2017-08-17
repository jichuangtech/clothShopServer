package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.UserAddressReqVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.service.UserAddressService;
import com.jichuangtech.clothshopserver.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserAddressConstant.API_USER_ADDRESS)
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;


    /**
     * 查找该用户的所有收货地址
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<UserAddressRespVO>> list(@PathVariable("userId") int userId) {
        Response<List<UserAddressRespVO>> response = new Response<List<UserAddressRespVO>>();
        response.data = userAddressService.list(userId);
        return response;
    }


    /**
     * 根据父地区获取子地区
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/region/{parentId}", method = RequestMethod.GET)
    public Response<List<RegionEntity>> list(@PathVariable("parentId") long parentId) {
        Response<List<RegionEntity>> response = new Response<List<RegionEntity>>();
        response.data = userAddressService.list(parentId);
        return response;
    }


    /**
     * 修改默认收货地址
     *
     * @param userId
     * @param addressId
     */
    @RequestMapping(value = "/defaultaddress", method = RequestMethod.POST)
    public Response updateDefaultAddress(@RequestParam("oldAddressId") int oldAddressId, @RequestParam("newAddressId") int newAddressId) {
        userAddressService.updateDefaultAddress(oldAddressId, newAddressId);
        return new Response();
    }


    /**
     * 新增收货地址
     *
     * @param userAddressReqVO
     * @return
     */
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public Response<UserAddressRespVO> saveUserAddress(@RequestBody UserAddressReqVO userAddressReqVO) {
        Response<UserAddressRespVO> response = new Response<UserAddressRespVO>();
        response.data = userAddressService.saveUserAddress(userAddressReqVO);
        System.out.println(JsonMapper.nonDefaultMapper().toJson(response.data));
        return response;
    }
}
