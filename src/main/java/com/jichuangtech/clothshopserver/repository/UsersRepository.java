package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{
    UsersEntity findByOpenid(String openId);
    UsersEntity findByMobile(String mobile);
    UsersEntity findByMobileAndPassword(String mobile, String password);
}
