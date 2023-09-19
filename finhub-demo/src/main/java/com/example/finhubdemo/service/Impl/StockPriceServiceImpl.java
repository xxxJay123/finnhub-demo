package com.example.finhubdemo.service.Impl;

import com.example.finhubdemo.model.StockPriceDTO;
import com.example.finhubdemo.service.APIService;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class StockPriceServiceImpl /* implements StockPriceService */ {

  @Autowired
  private APIService apiService;



  public List<StockPriceDTO> findStockPrice(String symbol) {
    try {
      // Call the API service to get the stock price for the given symbol
      /* String api = apiService.getQuoteBySymbol(symbol); */
      StockPriceDTO stockPrice = apiService.getQuoteBySymbol(symbol);
      log.info("Raw API response: " + stockPrice);
      if (stockPrice == null) {
        log.error("Failed to get stock price"); 
        return new ArrayList<>();
      }

      // Create a list and add the retrieved stock price to it
      List<StockPriceDTO> stockPrices = new ArrayList<>();
      stockPrices.add(stockPrice);

      return stockPrices;
    } catch (Exception e) {
      // Handle any exceptions that occur during the API call
      log.error("Error fetching stock price for symbol " + symbol, e);
      return Collections.emptyList();
    }
  }



}


