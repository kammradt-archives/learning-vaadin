package com.kammradt.learning.vaadin.backend.entity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public Long count() {
        return companyRepository.count();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
