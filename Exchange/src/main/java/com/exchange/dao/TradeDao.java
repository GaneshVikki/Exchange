package com.exchange.dao;

import java.util.List;

import com.exchange.domain.TradeRequest;
import com.exchange.entity.TradeEntity;

public interface TradeDao {
	
	public List<TradeEntity> getTrades(TradeRequest request);
	
	public TradeEntity insertTrade(TradeEntity trade);
}
