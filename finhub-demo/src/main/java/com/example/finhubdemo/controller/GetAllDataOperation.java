package com.example.finhubdemo.controller;


import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.finhubdemo.infra.ApiResponse;


public interface GetAllDataOperation{
  @GetMapping(value = "/getpiceandprofile")
  ResponseEntity<ApiResponse<Map<String, Object>>>  findStockPriceAndProfile(
      String symbol) throws Exception;
}
