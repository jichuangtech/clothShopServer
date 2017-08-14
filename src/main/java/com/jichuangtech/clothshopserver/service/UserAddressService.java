package com.jichuangtech.clothshopserver.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		for (int i = 0; i < userAddressEntityList.size(); i++) {
			UserAddressEntity userAddressEntity = userAddressEntityList.get(i);
			UserAddressVO userAddressVO = new UserAddressVO();
			userAddressVO.setAddressId(userAddressEntity.getAddressId());
			
			long country = userAddressEntity.getCountry();
			RegionEntity entity = regionRepository.findOne(country);
			userAddressVO.setCountry(entity.getName());
			
			long province = userAddressEntity.getProvince();
			entity = regionRepository.findOne(province);
			userAddressVO.setProvince(entity.getName());
			
			long city = userAddressEntity.getCity();
			entity = regionRepository.findOne(city);
			userAddressVO.setCity(entity.getName());
			
			long district = userAddressEntity.getDistrict();
			entity = regionRepository.findOne(district);
			userAddressVO.setDistrict(entity.getName());
			
			long twon = userAddressEntity.getTwon();
			entity = regionRepository.findOne(twon);
			userAddressVO.setTwon(entity.getName());
			userAddressVO.setAddress(userAddressEntity.getAddress());
			userAddressVO.setConsignee(userAddressEntity.getConsignee());
			userAddressVO.setMobile(userAddressEntity.getMobile());
			userAddressVO.setZipcode(userAddressEntity.getZipcode());
			userAddressVO.setIsDefault(userAddressEntity.getIsDefault());
			userAddressVOList.add(userAddressVO);
		}
		return userAddressVOList;
	}
	
	/**
	 * 根据父地区获取子地区信息
	 * @param parentId
	 * @return
	 */
	public List<RegionEntity> list(long parentId){
		return null;
	}
}
