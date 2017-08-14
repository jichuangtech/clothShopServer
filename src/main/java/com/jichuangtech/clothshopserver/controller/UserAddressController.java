package com.jichuangtech.clothshopserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.vo.UserAddressVO;
import com.jichuangtech.clothshopserver.service.UserAddressService;

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
	public List<UserAddressVO> list(@PathVariable("userId")int userId){
		return userAddressService.list(userId);
	}
	
	@RequestMapping(value="/address/{parentId}",method = RequestMethod.GET)
	@ResponseBody
	public List<RegionEntity> list(@PathVariable("parentId")long parentId){
		return userAddressService.list(parentId);
	}
}
