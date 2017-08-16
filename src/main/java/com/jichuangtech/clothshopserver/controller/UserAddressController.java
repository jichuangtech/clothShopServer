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
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.vo.UserAddressReqVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.service.UserAddressService;
import com.jichuangtech.clothshopserver.utils.JsonHelper;
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
	public Response<List<UserAddressRespVO>> list(@PathVariable("userId")int userId){
		Response<List<UserAddressRespVO>> response = new Response<List<UserAddressRespVO>>();
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
	public Response<UserAddressRespVO> saveUserAddress(@RequestBody UserAddressReqVO userAddressReqVO){
		Response<UserAddressRespVO> response = new Response<UserAddressRespVO>();
		response.data = userAddressService.saveUserAddress(userAddressReqVO);
		System.out.println(JsonHelper.getJson(response.data));
		return response;
	}
}
