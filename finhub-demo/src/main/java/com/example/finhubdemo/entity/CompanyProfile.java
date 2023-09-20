package com.example.finhubdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "company_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String country;
  private String currency;
  private String exchange;
  private String ipo;
  private long marketCapitalization;
  private String name;
  private String phone;
  private double shareOutstanding;
  private String ticker;
  private String weburl;
  private String logo;
  private String finnhubIndustry;
}
