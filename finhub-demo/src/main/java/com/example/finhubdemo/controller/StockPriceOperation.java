package com.example.finhubdemo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.model.StockPriceDTO;

public interface StockPriceOperation {
  @GetMapping(value = "/stockprice")
  ResponseEntity<ApiResponse<List<StockPriceDTO>>> findStockPrice(
      String symbol) throws Exception;
}
