package com.exchange.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.exchange.dao.TradeDao;
import com.exchange.domain.Trade;
import com.exchange.domain.TradeRequest;
import com.exchange.entity.TradeEntity;

public class TradeServiceImpl implements TradeService {
	
	@Autowired
	TradeDao tradeDao;
	
	@Override
	public List<Trade> getTrades(TradeRequest request) {
		List<TradeEntity> tradeEntities = tradeDao.getTrades(request);
		List<Trade> trades = new ArrayList<>();
		for(TradeEntity entity : tradeEntities) {
			Trade trade = new Trade();
			trade.setBuyer(entity.getBuyer());
			trade.setSeller(entity.getSeller());
			trade.setPrice(entity.getPrice());
			trade.setStock(entity.getStock());
			trade.setTradeDate(entity.getTradeDate());
			trades.add(trade);
		}
		return trades;
	}

}
