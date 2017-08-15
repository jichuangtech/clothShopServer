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

import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.UserAddressEntity;
import com.jichuangtech.clothshopserver.model.vo.UserAddressVO;
import com.jichuangtech.clothshopserver.service.UserAddressService;
import com.jichuangtech.clothshopserver.model.Response;
@Controller
@RequestMapping(UserAddressConstant.API_USER_ADDRESS)
public class UserAddressController {
	@Autowired
	private UserAddressService userAddressService;
	
	@RequestMapping(value="/{userId}",method = RequestMethod.GET)
	@ResponseBody
	/**
	 * 查找该用户的所有收货地址
	 * @param userId
	 * @return
	 */
	public Response<List<UserAddressVO>> list(@PathVariable("userId")int userId){
		Response<List<UserAddressVO>> response = new Response<List<UserAddressVO>>();
		response.data = userAddressService.list(userId);
		return response;
	}
	
	@RequestMapping(value="/region/{parentId}",method = RequestMethod.GET)
	@ResponseBody
	/**
	 * 根据父地区获取子地区
	 * @param parentId
	 * @return
	 */
	public Response<List<RegionEntity>> list(@PathVariable("parentId")long parentId){
		Response<List<RegionEntity>> response = new Response<List<RegionEntity>>();
		response.data = userAddressService.list(parentId);
		return response;
	}
	
	@RequestMapping(value="/defaultaddress",method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 修改默认收货地址
	 * @param userId
	 * @param addressId
	 */
	public Response updateDefaultAddress(@RequestParam("oldAddressId")int oldAddressId,@RequestParam("newAddressId")int newAddressId){
		userAddressService.updateDefaultAddress(oldAddressId,newAddressId);
		return new Response();
	}
	
	@RequestMapping(value="/address",method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 新增收货地址
	 * @param userAddressEntity
	 * @return
	 */
	public Response<UserAddressVO> saveUserAddress(@RequestBody UserAddressEntity userAddressEntity){
		Response<UserAddressVO> response = new Response<UserAddressVO>();
		response.data = userAddressService.saveUserAddress(userAddressEntity);
		return response;
	}
}
