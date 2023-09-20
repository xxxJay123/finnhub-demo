package com.example.finhubdemo.service;

import java.util.List;
import com.example.finhubdemo.entity.CompanyProfile;
import com.example.finhubdemo.infra.BusinessException;
import com.example.finhubdemo.model.CompanyProfileDTO;

public interface CompanyProfileService {
  public List<CompanyProfileDTO> findCompanyProfile(String symbol)
      throws BusinessException;

  public List<CompanyProfile> findProfileInDBById(Long id)
      throws BusinessException;
}
