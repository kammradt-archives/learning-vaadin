package com.kammradt.learning.vaadin.backend.contact;

import com.kammradt.learning.vaadin.backend.entity.BaseEntity;
import com.kammradt.learning.vaadin.backend.company.Company;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Contact extends BaseEntity {

    public enum Status {
        ImportedLead, NotContacted, Contacted, Customer, ClosedLost
    }

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Email
    @NotBlank
    private String email;


}