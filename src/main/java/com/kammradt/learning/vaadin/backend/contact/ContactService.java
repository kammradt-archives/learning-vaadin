package com.kammradt.learning.vaadin.backend.contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactService {

  private final ContactRepository contactRepository;

  public void save(Contact contact) {
    contactRepository.save(contact);
  }

  public List<Contact> findAll() {
    return contactRepository.findAll();
  }

  public List<Contact> findAll(String filterText) {
    if (filterText.isBlank()) return findAll();
    return contactRepository
        .findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(
            filterText, filterText, filterText);
  }

  public void delete(Contact contact) {
    contactRepository.delete(contact);
  }

  public long count() {
    return contactRepository.count();
  }
}
