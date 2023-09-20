package com.example.finhubdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.finhubdemo.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
 
}
