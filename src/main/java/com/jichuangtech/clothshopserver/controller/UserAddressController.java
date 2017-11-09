package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.service.SessionService;
import com.jichuangtech.clothshopserver.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private UsersService usersService;

    /**
     * 查找该用户的所有收货地址
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户所有收货地址")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response<List<UserAddressRespVO>> listAddress(@PathVariable("userId") int userId,
                                                         @RequestHeader("access_token") String accessToken) {
        userId = usersService.getUserIdByToken(accessToken);
        Response<List<UserAddressRespVO>> response = new Response<>();
        response.data = userAddressService.listAddress(userId);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ADDRESS_ALL_GET_ERROR);
        }
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
        Response<List<RegionEntity>> response = new Response<>();
        response.data = userAddressService.listRegion(parentId);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ADDRESS_REGION_GET_ERROR);
        }
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
    public Response<String> updateDefaultAddress(@PathVariable("userId") int userId,
                                                 @PathVariable("defaultAddressId") int defaultAddressId,
                                                 @RequestHeader("access_token") String accessToken) {
        userId = usersService.getUserIdByToken(accessToken);
        userAddressService.updateDefaultAddress(userId, defaultAddressId);
        return new Response<>();
    }
    
    /**
     * 返回默认收货地址
     *
     * @param userId
     * @param addressId
     */
    @ApiOperation(value = "获取用户默认地址")
    @RequestMapping(value = "{userId}/defaultaddress", method = RequestMethod.GET)
    public Response<UserAddressRespVO> getDefaultAddress(@PathVariable("userId")int userId,
                                                         @RequestHeader("access_token") String accessToken){
        userId = usersService.getUserIdByToken(accessToken);
    	UserAddressRespVO  userAddressRespVO = userAddressService.getDefaultAddress(userId);
    	Response<UserAddressRespVO> response = new Response<>();
    	response.data = userAddressRespVO;

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ADDRESS_DEFAULT_GET_ERROR);
        }
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
    public Response<UserAddressRespVO> saveUserAddress(@RequestBody UserAddressReqVO userAddressReqVO,
                                                       @RequestHeader("access_token") String accessToken) {
        Response<UserAddressRespVO> response = new Response<>();
        userAddressReqVO.setUserId(usersService.getUserIdByToken(accessToken));
        response.data = userAddressService.saveUserAddress(userAddressReqVO);
        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ADDRESS_ADD_ERROR);
        }
        return response;
    }
    
    /**
     * 删除指定收货地址
     *
     * @param userAddressReqVO
     * @return
     */
    @ApiOperation(value = "删除指定用户收货地址")
    @RequestMapping(value = "{userId}/address/{addressId}", method = RequestMethod.POST)
    public Response<String> deleteAddress(@PathVariable("userId")int userId,
                                          @PathVariable("addressId")int addressId,
                                          @RequestHeader("access_token") String accessToken){
        userId = usersService.getUserIdByToken(accessToken);
    	userAddressService.deleteAddressByAddressId(userId,addressId);
    	return new Response<>();
    }
    
    /**
     * 获取详细地址
     * @param addressId
     * @return
     */
    @ApiOperation(value = "获取详细用户收货地址信息", notes = "包括地区返回码和地区名")
    @RequestMapping(value = "/address/{addressId}", method = RequestMethod.GET)
    public Response<UserAddressRespDetailVO> getAddress(@PathVariable("addressId")int addressId){
    	UserAddressRespDetailVO  userAddressRespDetailVO = userAddressService.getDetailAddress(addressId);
    	Response<UserAddressRespDetailVO> response = new Response<>();
    	response.data = userAddressRespDetailVO;

        if(response.data == null) {
            response.setStatusCode(ResponseCode.CODE_ADDRESS_DETAIL_GET_ERROR);
        }
    	return response;
    }
}
