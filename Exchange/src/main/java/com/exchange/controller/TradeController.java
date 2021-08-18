package com.exchange.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.domain.Trade;
import com.exchange.domain.TradeRequest;
import com.exchange.service.TradeService;

@RestController
@RequestMapping(path = "exchange/trade")
public class TradeController {
	
	@Autowired
	TradeService tradeService;
	
	@GetMapping(path = "/get" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trade>> getTradesList(
			@RequestParam(name = "party", required = false) String party, 
			@RequestParam(name = "symbol", required = false) String symbol, 
			@RequestParam(name = "date", required = false) Timestamp date){
		HttpHeaders headers = new HttpHeaders();
		TradeRequest request = new TradeRequest();
		request.setParties(party);
		request.setStock(symbol);
		request.setTradeDate(date);
		List<Trade> trades = tradeService.getTrades(request);
		if(trades != null && !trades.isEmpty()) {
			return new ResponseEntity<List<Trade>>(trades, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trade>>(null, headers, HttpStatus.NOT_FOUND);
		}
	}
}
