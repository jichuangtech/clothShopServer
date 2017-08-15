package com.jichuangtech.clothshopserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.UserAddressEntity;
import com.jichuangtech.clothshopserver.model.vo.UserAddressVO;
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
	public List<UserAddressVO> list(int userId){
		List<UserAddressEntity> userAddressEntityList = userAddressRepository.findByUserId(userId);
		return getUserAddressVO(userAddressEntityList);
	}
	
	/**
	 * 查找收货地址详情
	 * @param userAddressEntityList
	 */
	private List<UserAddressVO> getUserAddressVO(List<UserAddressEntity> userAddressEntityList) {
		List<UserAddressVO> userAddressVOList = new ArrayList<UserAddressVO>();
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		for (int i = 0; i < userAddressEntityList.size(); i++) {
			
			UserAddressEntity userAddressEntity = userAddressEntityList.get(i);
			UserAddressVO userAddressVO = createUserAddressVO(userAddressEntity,regionIdNameMap);
			userAddressVOList.add(userAddressVO);
		}
		return userAddressVOList;
	}
	
	/**
	 * 生成收货地址返回对象
	 * @param userAddressEntity
	 * @param regionIdNameMap
	 */
	private UserAddressVO createUserAddressVO(UserAddressEntity userAddressEntity,
			Map<Long, String> regionIdNameMap) {
		UserAddressVO userAddressVO = new UserAddressVO();
		userAddressVO.setAddressId(userAddressEntity.getAddressId());
		
		int userId = userAddressEntity.getUserId();
		userAddressVO.setUserId(userId);
		
		long country = userAddressEntity.getCountry();
		String coutryName = regionIdNameMap.get(country);
		userAddressVO.setCountryName(coutryName);
		
		long province = userAddressEntity.getProvince();
		String provinceName = regionIdNameMap.get(province);
		userAddressVO.setProvinceName(provinceName);
		
		long city = userAddressEntity.getCity();
		String cityName = regionIdNameMap.get(city);
		userAddressVO.setCityName(cityName);
		
		long district = userAddressEntity.getDistrict();
		String districtName = regionIdNameMap.get(district);
		userAddressVO.setDistrictName(districtName);
		
		long twon = userAddressEntity.getTwon();
		String twonName = regionIdNameMap.get(twon);
		userAddressVO.setTwonName(twonName);
		
		userAddressVO.setAddress(userAddressEntity.getAddress());
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
			regionIdSet.add(userAddressEntity.getCountry());
			regionIdSet.add(userAddressEntity.getProvince());
			regionIdSet.add(userAddressEntity.getCity());
			regionIdSet.add(userAddressEntity.getDistrict());
			regionIdSet.add(userAddressEntity.getTwon());
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
	public UserAddressVO saveUserAddress(UserAddressEntity userAddressEntity){
		UserAddressEntity newUserAddressEntity = userAddressRepository.save(userAddressEntity);
		List<UserAddressEntity> userAddressEntityList = new ArrayList<UserAddressEntity>();
		userAddressEntityList.add(newUserAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		return createUserAddressVO(newUserAddressEntity,regionIdNameMap);
	}
}
