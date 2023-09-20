package com.example.finhubdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.finhubdemo.entity.CompanyProfile;

public interface CompanyProfileRepository
    extends JpaRepository<CompanyProfile, Long> {

}
