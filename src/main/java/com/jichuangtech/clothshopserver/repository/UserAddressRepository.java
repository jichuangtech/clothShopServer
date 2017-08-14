package com.jichuangtech.clothshopserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.UserAddressEntity;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer>{
	/**
	 * 根据用户id查找收货地址
	 * @param userId
	 * @return
	 */
	public List<UserAddressEntity> findByUserId(int userId);
}
