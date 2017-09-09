package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.ColorEntity;
import com.jichuangtech.clothshopserver.model.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ColorRepository extends JpaRepository<ColorEntity, Long>{

}
