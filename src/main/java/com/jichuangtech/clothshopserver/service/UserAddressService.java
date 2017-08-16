package com.jichuangtech.clothshopserver.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.UserAddressEntity;
import com.jichuangtech.clothshopserver.model.vo.Region;
import com.jichuangtech.clothshopserver.model.vo.UserAddressReqVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.repository.RegionRepository;
import com.jichuangtech.clothshopserver.repository.UserAddressRepository;

@Service

public class UserAddressService {
	@Autowired
	private UserAddressRepository userAddressRepository;
	@Autowired
	private RegionRepository regionRepository;
	/**
	 * 查找该用户的所有收货地址
	 * @param userId
	 * @return
	 */
	public List<UserAddressRespVO> list(int userId){
		List<UserAddressEntity> userAddressEntityList = userAddressRepository.findByUserId(userId);
		return getUserAddressVO(userAddressEntityList);
	}
	
	/**
	 * 查找收货地址详情
	 * @param userAddressEntityList
	 */
	private List<UserAddressRespVO> getUserAddressVO(List<UserAddressEntity> userAddressEntityList) {
		List<UserAddressRespVO> userAddressVOList = new ArrayList<UserAddressRespVO>();
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		for (int i = 0; i < userAddressEntityList.size(); i++) {
			
			UserAddressEntity userAddressEntity = userAddressEntityList.get(i);
			UserAddressRespVO userAddressVO = createUserAddressVO(userAddressEntity,regionIdNameMap);
			userAddressVOList.add(userAddressVO);
		}
		return userAddressVOList;
	}
	
	/**
	 * 生成收货地址返回对象
	 * @param userAddressEntity
	 * @param regionIdNameMap
	 */
	private UserAddressRespVO createUserAddressVO(UserAddressEntity userAddressEntity,
			Map<Long, String> regionIdNameMap) {
		UserAddressRespVO userAddressVO = new UserAddressRespVO();
		userAddressVO.setAddressId(userAddressEntity.getAddressId());
		
		int userId = userAddressEntity.getUserId();
		userAddressVO.setUserId(userId);
		
		long province = userAddressEntity.getProvince();
		String provinceName = regionIdNameMap.get(province);
		
		long city = userAddressEntity.getCity();
		String cityName = regionIdNameMap.get(city);
		
		long district = userAddressEntity.getDistrict();
		String districtName = regionIdNameMap.get(district);
		
		StringBuilder fullAddressBuilder = new StringBuilder();
		fullAddressBuilder.append(provinceName).append(cityName).append(districtName).append(userAddressEntity.getAddress());
		userAddressVO.setAddress(fullAddressBuilder.toString());
		
		userAddressVO.setConsignee(userAddressEntity.getConsignee());
		userAddressVO.setMobile(userAddressEntity.getMobile());
		userAddressVO.setZipcode(userAddressEntity.getZipcode());
		userAddressVO.setIsDefault(userAddressEntity.getIsDefault());
		return userAddressVO;
	}

	/**
	 * 获取地址
	 * @param userAddressEntityList
	 */
	private Map<Long,String> getRegionName(List<UserAddressEntity> userAddressEntityList) {
		Set<Long> regionIdSet = new HashSet<Long>();
		for (int i = 0; i < userAddressEntityList.size(); i++) {
			UserAddressEntity userAddressEntity = userAddressEntityList.get(i);
			regionIdSet.add(userAddressEntity.getProvince());
			regionIdSet.add(userAddressEntity.getCity());
			regionIdSet.add(userAddressEntity.getDistrict());
		}
		List<RegionEntity> regionEntityList = regionRepository.findByIdIn(regionIdSet);
		Map<Long,String> regionIdMap = new HashMap<Long,String>();
		for (int i = 0; i < regionEntityList.size(); i++) {
			RegionEntity regionEntity = regionEntityList.get(i);
			long regionId = regionEntity.getId();
			String regionName = regionEntity.getName();
			regionIdMap.put(regionId, regionName);
		}
		return regionIdMap;
	}

	/**
	 * 根据父地区获取子地区信息
	 * @param parentId
	 * @return
	 */
	public List<RegionEntity> list(long parentId){
		return regionRepository.findByParentId(parentId);
	}
	
	/**
	 * 修改默认收货地址
	 * @param userId
	 * @param addressId
	 */
	@Transactional
	public void updateDefaultAddress(int oldAddressId,int newAddressId){
		userAddressRepository.updateDefaultAddress(UserAddressConstant.NOT_DEFAULT_ADDRESS, oldAddressId);
		userAddressRepository.updateDefaultAddress(UserAddressConstant.DEFAULT_ADDRESS, newAddressId);
	}
	
	/**
	 * 添加收货地址
	 * @param userId
	 * @param userAddressVO
	 * @return
	 */
	public UserAddressRespVO saveUserAddress(UserAddressReqVO userAddressReqVO){
		UserAddressEntity userAddressEntity = createUserAddressEntity(userAddressReqVO);
		UserAddressEntity newUserAddressEntity = userAddressRepository.save(userAddressEntity);
		List<UserAddressEntity> userAddressEntityList = new ArrayList<UserAddressEntity>();
		userAddressEntityList.add(newUserAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		return createUserAddressVO(newUserAddressEntity,regionIdNameMap);
	}
	
	private UserAddressEntity createUserAddressEntity(
			UserAddressReqVO userAddressReqVO) {
		UserAddressEntity userAddressEntity = new UserAddressEntity();
		userAddressEntity.setAddress(userAddressReqVO.getAddress());
		userAddressEntity.setCity(userAddressReqVO.getCity());
		userAddressEntity.setConsignee(userAddressReqVO.getConsignee());
		userAddressEntity.setCountry(userAddressReqVO.getCountry());
		userAddressEntity.setDistrict(userAddressReqVO.getDistrict());
		userAddressEntity.setIsDefault(userAddressReqVO.getIsDefault());
		userAddressEntity.setMobile(userAddressReqVO.getMobile());
		userAddressEntity.setProvince(userAddressReqVO.getProvince());
		userAddressEntity.setTwon(userAddressReqVO.getTwon());
		userAddressEntity.setUserId(userAddressReqVO.getUserId());
		userAddressEntity.setZipcode(userAddressReqVO.getZipcode());
		return userAddressEntity;
	}

	/**
	 * 获取指定的地址
	 * @param addressId
	 * @return
	 */
	public UserAddressRespVO getAddress(int addressId){
		UserAddressEntity userAddressEntity = userAddressRepository.findOne(addressId);
		List<UserAddressEntity> userAddressEntityList = new ArrayList<UserAddressEntity>();
		userAddressEntityList.add(userAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		UserAddressRespVO userAddressVO = createUserAddressVO(userAddressEntity,regionIdNameMap);
		return userAddressVO;
	}

}
