package com.kammradt.learning.vaadin.backend.company;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  public Long count() {
    return companyRepository.count();
  }

  public Company save(Company company) {
    return companyRepository.save(company);
  }

  public List<Company> findAll() {
    return companyRepository.findAll();
  }
}
