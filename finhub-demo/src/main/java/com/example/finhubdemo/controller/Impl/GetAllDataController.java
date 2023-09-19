package com.example.finhubdemo.controller.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.finhubdemo.controller.GetAllDataOperation;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.infra.Code;
import com.example.finhubdemo.model.CompanyProfileDTO;
import com.example.finhubdemo.model.StockPriceDTO;
import com.example.finhubdemo.service.CompanyProfileService;
import com.example.finhubdemo.service.Impl.StockPriceServiceImpl;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/all")
@Slf4j
public class GetAllDataController implements GetAllDataOperation {
  @Autowired
  private CompanyProfileService companyProfileService;
  @Autowired
  private StockPriceServiceImpl stockPriceService;

  @Override //@GetMapping(value = "/getpiceandprofile")
  public ResponseEntity<ApiResponse<Map<String, Object>>> findStockPriceAndProfile(
      @RequestParam("symbol") String symbol) throws Exception {

    Map<String, Object> data = new HashMap<>();

    try {
      // Get company profile
      List<CompanyProfileDTO> companyProfile =
          companyProfileService.findCompanyProfile(symbol);
      data.put("companyProfile", companyProfile);

      // Get stock price
      List<StockPriceDTO> stockPrice = stockPriceService.findStockPrice(symbol);
      data.put("stockPrice", stockPrice);

      return ResponseEntity.ok(
          ApiResponse.<Map<String, Object>>builder().ok().data(data).build());

    } catch (Exception e) {
      // Handle exceptions here
      ApiResponse<Map<String, Object>> responseError =
          ApiResponse.<Map<String, Object>>builder()//
              .status(Code.JPH_NOTFOUND)//
              .build();//
      log.error("Error while fetching data for symbol " + symbol, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(responseError);
    }
  }
}
