package com.example.finhubdemo.service.Impl;

import com.example.finhubdemo.entity.CompanyProfile;
import com.example.finhubdemo.infra.BusinessException;
import com.example.finhubdemo.model.CompanyProfileDTO;
import com.example.finhubdemo.repository.CompanyProfileRepository;
import com.example.finhubdemo.service.APIService;
import com.example.finhubdemo.service.CompanyProfileService;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;


@Service
@Slf4j
public class CompanyProfileServiceImpl implements CompanyProfileService {

  @Autowired
  private APIService apiService;

  @Autowired
  private CompanyProfileRepository companyProfileRepository;

  @Override
  public List<CompanyProfileDTO> findCompanyProfile(String symbol) {
    try { // Call the API service to get the stock price for the given symbol
      CompanyProfileDTO companyProfileDTO =
          apiService.getProfile2BySymbol(symbol);
      log.info("Raw API response: " + companyProfileDTO);
      if (companyProfileDTO == null) {
        log.error("Failed to get company profile");
        return new ArrayList<>();
      }
      // Create a list and add the retrieved company profile to it
      CompanyProfile companyProfile =
          convertToPrrfolioEntity(companyProfileDTO);

      companyProfile = saveCompanyProfile(companyProfile);

      List<CompanyProfileDTO> companyProfiles = new ArrayList<>();
      companyProfiles.add(companyProfileDTO);

      return companyProfiles;

    } catch (Exception E) {
      // Handle any exceptions that occur during the API call
      log.error("Error fetching company profile for symbol " + symbol, E);
      return Collections.emptyList();

    }
  }

  @Override
  public List<CompanyProfile> findProfileInDBById(Long id)
      throws BusinessException {
    try {
      Optional<CompanyProfile> companyProfile =
          companyProfileRepository.findById(id);
      if (companyProfile.isPresent()) {
        return Collections.singletonList(companyProfile.get());
      } else {
        log.error("Failed to get company profile");
        return Collections.emptyList();
      }
    } catch (Exception E) {
      log.error("Error fetching company profile for id " + id, E);
      return Collections.emptyList();
    }
  }


  public CompanyProfile saveCompanyProfile(CompanyProfile companyProfile) {
    return companyProfileRepository.save(companyProfile);
  }

  private CompanyProfile convertToPrrfolioEntity(
      CompanyProfileDTO companyProfileDTO) {
    CompanyProfile companyProfile = new CompanyProfile();
    companyProfile.setCountry(companyProfileDTO.getCountry());
    companyProfile.setCurrency(companyProfileDTO.getCurrency());
    companyProfile.setExchange(companyProfileDTO.getExchange());
    companyProfile.setIpo(companyProfileDTO.getIpo());
    companyProfile
        .setMarketCapitalization(companyProfileDTO.getMarketCapitalization());
    companyProfile.setName(companyProfileDTO.getName());
    companyProfile.setPhone(companyProfileDTO.getPhone());
    companyProfile.setShareOutstanding(companyProfileDTO.getShareOutstanding());
    companyProfile.setTicker(companyProfileDTO.getTicker());
    companyProfile.setWeburl(companyProfileDTO.getWeburl());
    companyProfile.setLogo(companyProfileDTO.getLogo());
    companyProfile.setFinnhubIndustry(companyProfileDTO.getFinnhubIndustry());
    return companyProfile;
  }
}

