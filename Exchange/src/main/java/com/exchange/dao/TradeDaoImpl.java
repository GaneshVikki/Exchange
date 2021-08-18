package com.exchange.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.exchange.dao.repository.TradeRepository;
import com.exchange.dao.specification.TradeSpecification;
import com.exchange.domain.TradeRequest;
import com.exchange.entity.TradeEntity;

public class TradeDaoImpl implements TradeDao {
	
	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public List<TradeEntity> getTrades(TradeRequest request) {
		List<TradeEntity> trades = null;
		trades = tradeRepository.findAll(TradeSpecification.getOrder(request));
		return trades;
	}

	@Override
	public TradeEntity insertTrade(TradeEntity trade) {
		TradeEntity tradeEntity = tradeRepository.save(trade);
		return tradeEntity;
	}

}
