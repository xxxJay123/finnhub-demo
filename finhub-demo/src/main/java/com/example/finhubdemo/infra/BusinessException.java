package com.example.finhubdemo.infra;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private int code;

  public BusinessException(Code code) {
    super(code.getDesc());
    this.code = code.getCode();
  }
}
