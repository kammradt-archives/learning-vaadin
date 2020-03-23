package com.kammradt.learning.vaadin.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Contact extends AbstractEntity implements Cloneable {

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