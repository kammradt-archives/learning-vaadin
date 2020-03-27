package com.kammradt.learning.vaadin.backend.company;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public Map<String, Integer> getStats() {
    HashMap<String, Integer> stats = new HashMap<>();
    findAll().forEach(c -> stats.put(c.getName(), c.getEmployees().size()));
    return stats;
  }
}
