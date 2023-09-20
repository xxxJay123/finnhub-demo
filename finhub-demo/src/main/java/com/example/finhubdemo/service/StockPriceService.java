package com.example.finhubdemo.service;

import java.util.List;
import com.example.finhubdemo.entity.StockPrice;
import com.example.finhubdemo.infra.BusinessException;
import com.example.finhubdemo.model.StockPriceDTO;

public interface StockPriceService {
  public List<StockPriceDTO> findStockPrice(String symbol) throws BusinessException;

  public List<StockPrice> findPricesInDBById(Long id) throws BusinessException;

/*   public List<StockPriceDTO> findStockPriceBySymbol(String symbol)
      throws BusinessException;
} */
}