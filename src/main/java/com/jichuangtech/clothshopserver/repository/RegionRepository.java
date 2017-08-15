package com.jichuangtech.clothshopserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jichuangtech.clothshopserver.model.RegionEntity;

public interface RegionRepository extends JpaRepository<RegionEntity, Long>{
	List<RegionEntity> findByParentId(long parentId);
	List<RegionEntity> findByIdIn(Set<Long> regionIdSet);
}
