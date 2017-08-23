package com.jichuangtech.clothshopserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.UserAddressReqVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespDetailVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.service.UserAddressService;

@Api(description = "收货地址模块接口")
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
    @ApiOperation(value = "获取用户所有收货地址")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<UserAddressRespVO>> listAddress(@PathVariable("userId") int userId) {
        Response<List<UserAddressRespVO>> response = new Response<List<UserAddressRespVO>>();
        response.data = userAddressService.listAddress(userId);
        return response;
    }


    /**
     * 根据父地区获取子地区
     *
     * @param parentId
     * @return
     */
    @ApiOperation(value = "获取所有地区信息")
    @RequestMapping(value = "/region/{parentId}", method = RequestMethod.GET)
    public Response<List<RegionEntity>> listRegion(@PathVariable("parentId") long parentId) {
        Response<List<RegionEntity>> response = new Response<List<RegionEntity>>();
        response.data = userAddressService.listRegion(parentId);
        return response;
    }


    /**
     * 修改默认收货地址
     *
     * @param userId
     * @param addressId
     */
    @ApiOperation(value = "修改用户默认地址")
    @RequestMapping(value = "{userId}/defaultaddress/{defaultAddressId}", method = RequestMethod.POST)
    public Response<String> updateDefaultAddress(@PathVariable("userId") int userId, @PathVariable("defaultAddressId") int defaultAddressId) {
        userAddressService.updateDefaultAddress(userId, defaultAddressId);
        return new Response<String>();
    }
    
    /**
     * 返回默认收货地址
     *
     * @param userId
     * @param addressId
     */
    @ApiOperation(value = "获取用户默认地址")
    @RequestMapping(value = "{userId}/defaultaddress", method = RequestMethod.GET)
    public Response<UserAddressRespVO> getDefaultAddress(@PathVariable("userId")int userId){
    	UserAddressRespVO  userAddressRespVO = userAddressService.getDefaultAddress(userId);
    	Response<UserAddressRespVO> response = new Response<UserAddressRespVO>();
    	response.data = userAddressRespVO;
    	return response;
    }


    /**
     * 新增收货地址
     *
     * @param userAddressReqVO
     * @return
     */
    @ApiOperation(value = "新增用户收货地址",notes = "用户第一次添加收货地址时该地址为默认收货地址")
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public Response<UserAddressRespVO> saveUserAddress(@RequestBody UserAddressReqVO userAddressReqVO) {
        Response<UserAddressRespVO> response = new Response<UserAddressRespVO>();
        response.data = userAddressService.saveUserAddress(userAddressReqVO);
        return response;
    }
    
    /**
     * 删除指定收货地址
     *
     * @param userAddressReqVO
     * @return
     */
    @ApiOperation(value = "删除指定用户收货地址")
    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.POST)
    public Response<String> deleteAddress(@PathVariable("addressId")int addressId){
    	userAddressService.deleteAddressByAddressId(addressId);
    	return new Response<String>();
    }
    
    /**
     * 获取详细地址
     * @param addressId
     * @return
     */
    @ApiOperation(value = "获取详细用户收货地址信息", notes = "地区返回的是地区码")
    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.GET)
    public Response<UserAddressRespDetailVO> getAddress(@PathVariable("addressId")int addressId){
    	UserAddressRespDetailVO  userAddressRespDetailVO = userAddressService.getDetailAddress(addressId);
    	Response<UserAddressRespDetailVO> response = new Response<UserAddressRespDetailVO>();
    	response.data = userAddressRespDetailVO;
    	return response;
    }
}
