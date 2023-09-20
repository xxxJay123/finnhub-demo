package com.example.finhubdemo.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface StockDataFromDBOperation {
   @GetMapping(value = "/getpiceandprofile")
   ResponseEntity<Map<String, Object>>  findStockPriceAndProfileFromDB(
      Long id) throws Exception;
}
