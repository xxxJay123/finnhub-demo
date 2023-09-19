package com.example.finhubdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  /**
   * RestTemplate is a tool/ class (library) to invoke third party APIs. restTemplate object can be with lots of states/ Variables.
   * 
   * @return an object of RestTemplate
   */
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
