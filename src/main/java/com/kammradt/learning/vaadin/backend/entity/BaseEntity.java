package com.kammradt.learning.vaadin.backend.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private UUID id;

    public boolean isPersisted() {
        return id != null;
    }
}
