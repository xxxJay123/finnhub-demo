package com.example.finhubdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.finhubdemo.model.CompanyProfileDTO;
import com.example.finhubdemo.model.StockPriceDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class APIService {
  @Value(value = "${api.finnhub.apikey}")
  private String apiKey;

  @Value(value = "${api.finnhub.domain}")
  private String baseUrl;

  @Value(value = "${api.finnhub.function.quote}")
  private String quote;

  @Value(value = "${api.finnhub.function.profile2}")
  private String profile2;

  @Autowired
  private RestTemplate restTemplate;



  public CompanyProfileDTO getProfile2BySymbol(String symbol) {
    String apiUrl = UriComponentsBuilder.newInstance()//
        .scheme("https")// .scheme("https")
        .host(baseUrl)//
        .path(profile2)//
        .queryParam("symbol", symbol)//
        .queryParam("token", apiKey)//
        .build()//
        .toUriString();//

    log.info("apiUrl: (in APIService)" + apiUrl);
    try {
      ResponseEntity<CompanyProfileDTO> response =
          restTemplate.getForEntity(apiUrl, CompanyProfileDTO.class);
      String rawResponse = restTemplate.getForObject(apiUrl, String.class);
      log.info("Raw response: " + rawResponse);
      if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
      } else {
        log.error("API request failed with status code: {}", response);
        return null;
      } // Return null in case of an error
    } catch (Exception e) {
      log.error("Error calling API: ", e);
      return null; // Return null in case of an exception
    }


  }

  public StockPriceDTO getQuoteBySymbol(String symbol) {
    String apiUrl = UriComponentsBuilder.newInstance()//
        .scheme("https")// .scheme("https")
        .host(baseUrl)//
        .path(quote)//
        .queryParam("symbol", symbol)//
        .queryParam("token", apiKey)//
        .build()//
        .toUriString();//
    log.info("apiUrl: (in APIService)" + apiUrl);
    try {
      ResponseEntity<StockPriceDTO> response =
          restTemplate.getForEntity(apiUrl, StockPriceDTO.class);
      String rawResponse = restTemplate.getForObject(apiUrl, String.class);
      log.info("Raw response: " + rawResponse);
      if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
      } else {
        log.error("API request failed with status code: {}", response);
        return null; // Return null in case of an error
      }
    } catch (Exception e) {
      log.error("Error calling API: ", e);
      return null; // Return null in case of an exception
    }
  }
}
