package com.kammradt.learning.vaadin.ui;

import com.kammradt.learning.vaadin.backend.company.Company;
import com.kammradt.learning.vaadin.backend.contact.Contact;
import com.kammradt.learning.vaadin.backend.contact.ContactService;
import com.kammradt.learning.vaadin.ui.contact.ContactForm;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    ContactForm form;
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    private ContactService contactService;

    public MainView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureFilter();

        form = new ContactForm();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(filterText, content);
        updateList();
    }

    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
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

    private void configureFilter() {
        filterText.setPlaceholder("Search \uD83D\uDD0E");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }


}
