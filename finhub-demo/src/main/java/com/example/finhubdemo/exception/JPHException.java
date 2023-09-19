package com.example.finhubdemo.exception;

import com.example.finhubdemo.infra.BusinessException;
import com.example.finhubdemo.infra.Code;

public class JPHException extends BusinessException {

  public JPHException(Code code) {
    super(code);
  }

}