package com.example.finhubdemo.controller.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.finhubdemo.controller.CompanyProfileOperation;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.infra.Code;
import com.example.finhubdemo.model.CompanyProfileDTO;
import com.example.finhubdemo.service.CompanyProfileService;


@RestController
@RequestMapping("/api/getall")
public class CompanyProfileController implements CompanyProfileOperation {

  @Autowired
  private CompanyProfileService companyProfileService;


  @Override //  @GetMapping(value = "/comepany-profile")
  public ResponseEntity<ApiResponse<List<CompanyProfileDTO>>> findCompanyProfile(
      @RequestParam("symbol") String symbol) throws Exception {
    try {
      List<CompanyProfileDTO> companyProfile =
          companyProfileService.findCompanyProfile(symbol);
        

      ApiResponse<List<CompanyProfileDTO>> response =
          ApiResponse.<List<CompanyProfileDTO>>builder() //
              .ok() //
              .data(companyProfile) //
              .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ApiResponse<List<CompanyProfileDTO>> responseError =
          ApiResponse.<List<CompanyProfileDTO>>builder() //
              .status(Code.NOTFOUND)//
              .build();//
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(responseError);
    }
  }
}

