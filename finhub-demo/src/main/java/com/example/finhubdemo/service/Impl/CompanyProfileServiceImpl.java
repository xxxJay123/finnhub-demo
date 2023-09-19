package com.example.finhubdemo.service.Impl;

import com.example.finhubdemo.model.CompanyProfileDTO;
import com.example.finhubdemo.service.APIService;
import com.example.finhubdemo.service.CompanyProfileService;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;


@Service
@Slf4j
public class CompanyProfileServiceImpl implements CompanyProfileService {

  @Autowired
  private APIService apiService;

  @Override
  public List<CompanyProfileDTO> findCompanyProfile(String symbol) {
    try { // Call the API service to get the stock price for the given symbol
      CompanyProfileDTO companyProfile = apiService.getProfile2BySymbol(symbol);
      log.info("Raw API response: " + companyProfile);
      if (companyProfile == null) {
        log.error("Failed to get company profile");
        return new ArrayList<>();
      }
      // Create a list and add the retrieved company profile to it
      List<CompanyProfileDTO> companyProfiles = new ArrayList<>();
      companyProfiles.add(companyProfile);

      return companyProfiles;

    } catch (Exception E) {
      // Handle any exceptions that occur during the API call
      log.error("Error fetching company profile for symbol " + symbol, E);
      return Collections.emptyList();

    }
  }
}

