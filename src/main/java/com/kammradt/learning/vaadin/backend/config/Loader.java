package com.kammradt.learning.vaadin.backend.config;

import com.kammradt.learning.vaadin.backend.company.Company;
import com.kammradt.learning.vaadin.backend.company.CompanyService;
import com.kammradt.learning.vaadin.backend.contact.Contact;
import com.kammradt.learning.vaadin.backend.contact.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Loader implements ApplicationRunner {

  private final CompanyService companyService;
  private final ContactService contactService;

  @Override
  public void run(ApplicationArguments args) {
    if (companyService.count() == 0) {
      createCompanies();
    }
  }

  private void createCompanies() {
    var c1 = new Company();
    c1.setName("Firma da Gigi");
    companyService.save(c1);

    var c2 = new Company();
    c2.setName("Firma do Cast");
    companyService.save(c2);

    var p1 = new Contact();
    p1.setFirstName("Vinizin");
    p1.setLastName("Kammradt");
    p1.setEmail("vinicius.kammradt1@gmail.com");
    p1.setStatus(Contact.Status.Customer);
    p1.setCompany(c1);
    contactService.save(p1);

    var p2 = new Contact();
    p2.setFirstName("Alvezin");
    p2.setLastName("Famoso");
    p2.setEmail("alves.engenheiro@gmail.com");
    p2.setStatus(Contact.Status.Contacted);
    contactService.save(p2);
  }
}
