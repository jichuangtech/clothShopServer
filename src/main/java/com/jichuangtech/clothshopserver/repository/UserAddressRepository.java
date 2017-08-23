package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jichuangtech.clothshopserver.model.UserAddressEntity;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer>{
	/**
	 * 根据用户id查找收货地址
	 * @param userId
	 * @return
	 */
	public List<UserAddressEntity> findByUserId(int userId);
	
	public UserAddressEntity findByUserIdAndIsDefault(int userId,byte isDefault);
	
	@Modifying 
	@Query("update UserAddressEntity set is_default = ?1 where user_id = ?2") 
	/**
	 * 根据userId更新是否默认地址
	 * @param isDefault
	 * @param userId
	 * @return
	 */
	int updateIsDefaultByUserId(int isDefault, int userId); 
	
	@Modifying 
	@Query("update UserAddressEntity set is_default = ?1 where address_id = ?2") 
	/**
	 * 根据addressId更新是否默认地址
	 * @param isDefault
	 * @param addressId
	 * @return
	 */
	int updateIsDefaultByAddressId(int isDefault, int addressId); 
}
