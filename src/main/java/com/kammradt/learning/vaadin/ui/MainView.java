package com.kammradt.learning.vaadin.ui;

import com.kammradt.learning.vaadin.backend.entity.Company;
import com.kammradt.learning.vaadin.backend.entity.Contact;
import com.kammradt.learning.vaadin.backend.entity.ContactService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    private ContactService contactService;

    public MainView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(contactService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("contract-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");

        grid.getColumns().forEach(this::enableAutoWidth);
    }

    private void enableAutoWidth(Grid.Column<Contact> col) {
        col.setAutoWidth(true);
    }

}
