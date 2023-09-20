package com.example.finhubdemo.service.Impl;

import com.example.finhubdemo.entity.StockPrice;
import com.example.finhubdemo.infra.BusinessException;
import com.example.finhubdemo.model.StockPriceDTO;
import com.example.finhubdemo.repository.StockPriceRepository;
import com.example.finhubdemo.service.APIService;
import com.example.finhubdemo.service.StockPriceService;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class StockPriceServiceImpl  implements StockPriceService  {

  @Autowired
  private APIService apiService;

  @Autowired
  private StockPriceRepository stockPriceRepository;


   @Override
  public List<StockPriceDTO> findStockPrice(String symbol)
      throws BusinessException {
    try {
      // Call the API service to get the stock price for the given symbol
      StockPriceDTO stockPriceDTO = apiService.getQuoteBySymbol(symbol);
      log.info("Raw API response: " + stockPriceDTO);
      if (stockPriceDTO == null) {
        log.error("Failed to get stock price");
        return new ArrayList<>();
      }

      // Convert StockPriceDTO to StockPrice entity
      StockPrice stockPrice = convertToStockPriceEntity(stockPriceDTO);

      // Save the StockPrice entity to the database
      stockPrice = stockPriceRepository.save(stockPrice);

      // Create a list and add the retrieved stock price to it
      List<StockPriceDTO> stockPrices = new ArrayList<>();
      stockPrices.add(stockPriceDTO);

      return stockPrices;
    } catch (Exception e) {
      // Handle any exceptions that occur during the API call
      log.error("Error fetching stock price for symbol " + symbol, e);
      return Collections.emptyList();
    }
  }

  @Override
  public List<StockPrice> findPricesInDBById(Long id) throws BusinessException {
    try {
      // Use the StockPriceRepository to fetch StockPrice entity by its id
      Optional<StockPrice> stockPriceOptional = stockPriceRepository.findById(id);
      
      if (stockPriceOptional.isPresent()) {
          // Convert the Optional to a List
          return Collections.singletonList(stockPriceOptional.get());
      } else {
          log.error("No stock price found for id: " + id);
          return Collections.emptyList();
      }
  } catch (Exception e) {
      // Handle any exceptions that occur during the database query
      log.error("Error fetching stock price by id: " + id, e);
      return Collections.emptyList();
  }
}

  public StockPrice saveStockPrice(StockPrice stockPrice) {
    return stockPriceRepository.save(stockPrice);
  }

  private StockPrice convertToStockPriceEntity(StockPriceDTO stockPriceDTO) {
    StockPrice stockPrice = new StockPrice();
    stockPrice.setCurrentPrice(stockPriceDTO.getC());
    stockPrice.setChange(stockPriceDTO.getD());
    stockPrice.setPercentChange(stockPriceDTO.getDp());
    stockPrice.setHighPrice(stockPriceDTO.getH());
    stockPrice.setLowPrice(stockPriceDTO.getL());
    stockPrice.setOpenPrice(stockPriceDTO.getO());
    stockPrice.setPreviousClosePrice(stockPriceDTO.getPc());
    stockPrice.setTimestamp(stockPriceDTO.getT());
    return stockPrice;
  }
}


