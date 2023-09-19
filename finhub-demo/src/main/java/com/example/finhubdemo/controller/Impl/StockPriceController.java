package com.example.finhubdemo.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.finhubdemo.controller.StockPriceOperation;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.infra.Code;
import com.example.finhubdemo.model.StockPriceDTO;
import com.example.finhubdemo.service.Impl.StockPriceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/api/squote")
@Slf4j
public class StockPriceController implements StockPriceOperation {
  @Autowired
  private StockPriceServiceImpl stockPriceService;

  @Override // @GetMapping(value = "/stockprice")
  public ResponseEntity<ApiResponse<List<StockPriceDTO>>> findStockPrice(
      @RequestParam("symbol") String symbol) throws Exception {
    try {
      List<StockPriceDTO> stockPrice = stockPriceService.findStockPrice(symbol);
      ApiResponse<List<StockPriceDTO>> response =
          ApiResponse.<List<StockPriceDTO>>builder() //
              .ok() //
              .data(stockPrice) //
              .build();//
      
      log.info("stockPrice: " + stockPrice.toString());
      log.info("response: " + response);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ApiResponse<List<StockPriceDTO>> responseError =
          ApiResponse.<List<StockPriceDTO>>builder() //
              .status(Code.NOTFOUND)//
              .build();//
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(responseError);
    }
  }
}
