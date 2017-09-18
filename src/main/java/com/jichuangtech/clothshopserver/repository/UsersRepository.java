package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{
}
