package com.example.finhubdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyProfileDTO {
   @JsonProperty("country")
    private String country;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("ipo")
    private String ipo;

    @JsonProperty("marketCapitalization")
    private long marketCapitalization;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("shareOutstanding")
    private double shareOutstanding;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("weburl")
    private String weburl;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("finnhubIndustry")
    private String finnhubIndustry;


}
