package com.kammradt.learning.vaadin.backend.entity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactService {

    private ContactRepository contactRepository;

    public void save(Contact contact) {
        contactRepository.save(contact);
    }
}
