package com.jichuangtech.clothshopserver.repository;

import com.jichuangtech.clothshopserver.model.GoodsCartEntity;
import com.jichuangtech.clothshopserver.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsCartRepository extends JpaRepository<GoodsCartEntity, Integer> {
	List<GoodsCartEntity> findAllByUserId(int userId);
	GoodsCartEntity findByUserIdAndId(int userId, int cartId);
}
