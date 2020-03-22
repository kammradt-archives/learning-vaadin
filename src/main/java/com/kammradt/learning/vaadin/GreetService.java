package com.kammradt.learning.vaadin;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Salve seu tímido";
        } else {
            return "Salve " + name + " !";
        }
    }

}
