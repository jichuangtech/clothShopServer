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
import com.jichuangtech.clothshopserver.constant.UserAddressConstant;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import com.jichuangtech.clothshopserver.model.UserAddressEntity;
import com.jichuangtech.clothshopserver.model.vo.UserAddressReqVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespDetailVO;
import com.jichuangtech.clothshopserver.model.vo.UserAddressRespVO;
import com.jichuangtech.clothshopserver.repository.RegionRepository;
import com.jichuangtech.clothshopserver.repository.UserAddressRepository;
import com.jichuangtech.clothshopserver.utils.ListUtils;

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
	public List<UserAddressRespVO> listAddress(int userId){
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
	public List<RegionEntity> listRegion(long parentId){
		return regionRepository.findByParentId(parentId);
	}
	
	/**
	 * 修改默认收货地址
	 * @param oldAddressId
	 * @param newAddressId
	 */
	@Transactional
	public void updateDefaultAddress(int userId,int defaultAddressId){
		userAddressRepository.updateIsDefaultByUserId(UserAddressConstant.NOT_DEFAULT_ADDRESS, userId);
		userAddressRepository.updateIsDefaultByAddressId(UserAddressConstant.DEFAULT_ADDRESS, defaultAddressId);
	}
	
	/**
	 * 添加收货地址
	 * @param userId
	 * @param userAddressVO
	 * @return
	 */
	public UserAddressRespVO saveUserAddress(UserAddressReqVO userAddressReqVO){
		int userId = userAddressReqVO.getUserId();
		List<UserAddressEntity> userList = userAddressRepository.findByUserId(userId);
		if(ListUtils.isEmpty(userList)){
			userAddressReqVO.setIsDefault(UserAddressConstant.DEFAULT_ADDRESS);
		}
		UserAddressEntity userAddressEntity = createUserAddressEntity(userAddressReqVO);
		UserAddressEntity newUserAddressEntity = userAddressRepository.save(userAddressEntity);
		List<UserAddressEntity> userAddressEntityList = entityToList(newUserAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		return createUserAddressVO(newUserAddressEntity,regionIdNameMap);
	}
	
	private UserAddressEntity createUserAddressEntity(
			UserAddressReqVO userAddressReqVO) {
		UserAddressEntity userAddressEntity = new UserAddressEntity();
		userAddressEntity.setAddressId(userAddressReqVO.getAddressId());
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
	public UserAddressRespVO getAddressByAddressId(int addressId){
		UserAddressEntity userAddressEntity = userAddressRepository.findOne(addressId);
		UserAddressRespVO userAddressRespVO = getUserAddressRespVO(userAddressEntity);
		return userAddressRespVO;
	}
	
	/**
	 * 获取返回地址
	 * @param userAddressEntity
	 */
	private UserAddressRespVO getUserAddressRespVO(UserAddressEntity userAddressEntity) {
		List<UserAddressEntity> userAddressEntityList = entityToList(userAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		UserAddressRespVO userAddressVO = createUserAddressVO(userAddressEntity,regionIdNameMap);
		return userAddressVO;
	}

	/**
	 * 删除指定收货地址,如果删除的是默认地址需要新指定一个默认地址
	 * @param addressId
	 */
	@Transactional
	public void deleteAddressByAddressId(int userId,int deleteAddressId){
		List<UserAddressEntity> userAddressEntityList = userAddressRepository.findByUserId(userId);
		Map<Byte,List<UserAddressEntity>> isDefaultEntityMap = ListUtils.listToListMap(byte.class, userAddressEntityList, "isDefault");
		userAddressRepository.delete(deleteAddressId);
		List<UserAddressEntity> defaultAddressEntityList = isDefaultEntityMap.get(UserAddressConstant.DEFAULT_ADDRESS);
		UserAddressEntity defaultAddressEntity = defaultAddressEntityList.get(0);
		int defaultAddressId = defaultAddressEntity.getAddressId();
		if(defaultAddressId == deleteAddressId){
			List<UserAddressEntity> notDefaultAddressEntityList = isDefaultEntityMap.get(UserAddressConstant.NOT_DEFAULT_ADDRESS);
			UserAddressEntity newDefaultAddressEntity = notDefaultAddressEntityList.get(0);
			int newDefaultAddressId = newDefaultAddressEntity.getAddressId();
			userAddressRepository.updateIsDefaultByAddressId(UserAddressConstant.DEFAULT_ADDRESS, newDefaultAddressId);
		}
		
	}
	
	/**
	 * 获取该用户的默认收货地址
	 * @param userId
	 * @return
	 */
	public UserAddressRespVO getDefaultAddress(int userId){
		UserAddressEntity userAddressEntity = userAddressRepository.findByUserIdAndIsDefault(userId,UserAddressConstant.DEFAULT_ADDRESS);
		UserAddressRespVO userAddressRespVO = getUserAddressRespVO(userAddressEntity);
		return userAddressRespVO;
	}

	/**
	 * 获取详细收货地址
	 * @param addressId
	 * @return
	 */
	public UserAddressRespDetailVO getDetailAddress(int addressId){
		UserAddressEntity userAddressEntity = userAddressRepository.findOne(addressId);
		return createUserAddressRespDetailVO(userAddressEntity);
	}

	
	private UserAddressRespDetailVO createUserAddressRespDetailVO(UserAddressEntity userAddressEntity){
		List<UserAddressEntity> userAddressEntityList = entityToList(userAddressEntity);
		Map<Long,String> regionIdNameMap = getRegionName(userAddressEntityList);
		UserAddressRespDetailVO userAddressRespDetailVO = new UserAddressRespDetailVO();
		userAddressRespDetailVO.setAddress(userAddressEntity.getAddress());
		userAddressRespDetailVO.setAddressId(userAddressEntity.getAddressId());
		long cityCode = userAddressEntity.getCity();
		userAddressRespDetailVO.setCityCode(cityCode);
		userAddressRespDetailVO.setCityName(regionIdNameMap.get(cityCode));
		userAddressRespDetailVO.setConsignee(userAddressEntity.getConsignee());
		long districtCode = userAddressEntity.getDistrict();
		userAddressRespDetailVO.setDistrictCode(districtCode);
		userAddressRespDetailVO.setDistrictName(regionIdNameMap.get(districtCode));
		userAddressRespDetailVO.setIsDefault(userAddressEntity.getIsDefault());
		userAddressRespDetailVO.setMobile(userAddressEntity.getMobile());
		long provinceCode = userAddressEntity.getProvince();
		userAddressRespDetailVO.setProvinceCode(provinceCode);
		userAddressRespDetailVO.setProvinceName(regionIdNameMap.get(provinceCode));
		userAddressRespDetailVO.setUserId(userAddressEntity.getUserId());
		userAddressRespDetailVO.setZipcode(userAddressEntity.getZipcode());
		return userAddressRespDetailVO;
	}

	/**
	 * 单个实体封装成list
	 * @param userAddressEntity
	 * @return
	 */
	private List<UserAddressEntity> entityToList(
			UserAddressEntity userAddressEntity) {
		List<UserAddressEntity> userAddressEntityList = new ArrayList<UserAddressEntity>();
		userAddressEntityList.add(userAddressEntity);
		return userAddressEntityList;
	}
}
