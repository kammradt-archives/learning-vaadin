package com.kammradt.learning.vaadin.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Company extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Contact> employees;

}