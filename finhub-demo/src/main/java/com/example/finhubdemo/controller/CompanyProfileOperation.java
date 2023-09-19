package com.example.finhubdemo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.model.CompanyProfileDTO;

public interface CompanyProfileOperation {
  @GetMapping(value = "/comepany-profile")
  ResponseEntity<ApiResponse<List<CompanyProfileDTO>>> findCompanyProfile(
      String symbol) throws Exception;
}
