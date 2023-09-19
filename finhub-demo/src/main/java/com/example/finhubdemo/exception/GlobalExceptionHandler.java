package com.example.finhubdemo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.finhubdemo.infra.ApiResponse;
import com.example.finhubdemo.infra.Code;

@RestControllerAdvice // @ResponseBody + @ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = JPHException.class)
  public ResponseEntity<ApiResponse<Void>> jphExceptionHandler() {
    ApiResponse<Void> response = ApiResponse.<Void>builder() //
        .status(Code.JPH_NOTFOUND) //
        .data(null) //
        .build();
    return ResponseEntity.badRequest().body(response);
  }
}