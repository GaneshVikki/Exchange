package com.exchange.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.exchange.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>,
				JpaSpecificationExecutor<OrderEntity> {
	
	List<OrderEntity> findByOrderType(String orderType);
	
}
