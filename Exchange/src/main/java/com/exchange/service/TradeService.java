package com.exchange.service;

import java.util.List;

import com.exchange.domain.Trade;
import com.exchange.domain.TradeRequest;

public interface TradeService {

	List<Trade> getTrades(TradeRequest request);

}
