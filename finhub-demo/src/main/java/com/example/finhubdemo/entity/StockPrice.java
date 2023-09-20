package com.example.finhubdemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPrice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "current_price")
  private double currentPrice;

  @Column(name = "change")
  private double change;

  @Column(name = "percent_change")
  private double percentChange;

  @Column(name = "high_price")
  private double highPrice;

  @Column(name = "low_price")
  private double lowPrice;

  @Column(name = "open_price")
  private double openPrice;

  @Column(name = "previous_close_price")
  private double previousClosePrice;

  @Column(name = "timestamp")
  private long timestamp;
}
