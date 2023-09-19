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
public class StockPriceDTO {
  @JsonProperty("c")
  private double c;

  @JsonProperty("d")
  private double d;

  @JsonProperty("dp")
  private double dp;

  @JsonProperty("h")
  private double h;

  @JsonProperty("l")
  private double l;

  @JsonProperty("o")
  private double o;

  @JsonProperty("pc")
  private double pc;

  @JsonProperty("t")
  private long t;


}
