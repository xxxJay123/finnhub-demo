package com.example.finhubdemo.controller.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finhubdemo.controller.StockDataFromDBOperation;
import com.example.finhubdemo.entity.CompanyProfile;
import com.example.finhubdemo.entity.StockPrice;

import com.example.finhubdemo.service.CompanyProfileService;
import com.example.finhubdemo.service.Impl.StockPriceServiceImpl;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/sql")
@Slf4j
public class StockDataFromDBController implements StockDataFromDBOperation {
  @Autowired
  private CompanyProfileService companyProfileService;
  @Autowired
  private StockPriceServiceImpl stockPriceService;

  @Override
  /* @GetMapping(value = "/getpiceandprofile") */
  public ResponseEntity<Map<String, Object>> findStockPriceAndProfileFromDB(
      @RequestParam("id") Long id) {
    try {
      // Find stock price data from the database
      List<StockPrice> stockPrices = stockPriceService.findPricesInDBById(id);

      // Find company profile data from the database
      List<CompanyProfile> companyProfiles =
          companyProfileService.findProfileInDBById(id);

      // Create a response map
      Map<String, Object> responseMap = new HashMap<>();
      responseMap.put("stockPrices", stockPrices);
      responseMap.put("companyProfiles", companyProfiles);

      // Return the response
      return ResponseEntity.ok(responseMap);
    } catch (Exception e) {
      log.error("Error fetching stock price and company profile for id " + id,
          e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("error", "Error fetching data"));
    }
  }
}


