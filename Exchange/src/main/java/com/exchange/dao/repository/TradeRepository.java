package com.exchange.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.exchange.entity.TradeEntity;

public interface TradeRepository extends JpaRepository<TradeEntity, Long>,
						JpaSpecificationExecutor<TradeEntity> {

}
