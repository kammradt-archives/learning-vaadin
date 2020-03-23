package com.kammradt.learning.vaadin.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Company extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Contact> employees;

}