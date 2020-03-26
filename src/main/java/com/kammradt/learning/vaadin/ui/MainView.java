package com.kammradt.learning.vaadin.ui;

import com.kammradt.learning.vaadin.backend.company.Company;
import com.kammradt.learning.vaadin.backend.company.CompanyService;
import com.kammradt.learning.vaadin.backend.contact.Contact;
import com.kammradt.learning.vaadin.backend.contact.ContactService;
import com.kammradt.learning.vaadin.ui.contact.ContactForm;
import com.kammradt.learning.vaadin.ui.contact.ContactFormEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

  private final ContactService contactService;
  private final CompanyService companyService;

  public MainView(ContactService contactService, CompanyService companyService) {
    this.contactService = contactService;
    this.companyService = companyService;
    addClassName("list-view");
    setSizeFull();

    configureGrid();
    configureForm();

    add(getToolbar(), getPageContent());

    updateList();
    closeEditor();
  }

  private Div getPageContent() {
    Div content = new Div(grid, form);
    content.addClassName("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    form = new ContactForm(companyService.findAll());
    form.addListener(ContactFormEvent.SaveEvent.class, this::saveContact);
    form.addListener(ContactFormEvent.DeleteEvent.class, this::deleteContact);
    form.addListener(ContactFormEvent.CloseEvent.class, e -> closeEditor());
  }

  private void deleteContact(ContactFormEvent.DeleteEvent event) {
    contactService.delete(event.getContact());
    updateList();
    closeEditor();
  }

  private void saveContact(ContactFormEvent.SaveEvent event) {
    contactService.save(event.getContact());
    updateList();
    closeEditor();
  }

  private void updateList() {
    grid.setItems(contactService.findAll(filterText.getValue()));
  }

  private void closeEditor() {
    form.setContact(null);
    form.setVisible(false);
    removeClassName("editing");
  }

  private void configureGrid() {
    grid.addClassName("contact-grid");
    grid.setSizeFull();
    grid.removeColumnByKey("company");
    grid.setColumns("firstName", "lastName", "email", "status");
    grid.addColumn(this::getCompanyFormattedName).setHeader("Company");

    grid.getColumns().forEach(this::enableAutoWidth);

    grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));
  }

  private void editContact(Contact contact) {
    if (contact == null) {
      closeEditor();
    } else {
      form.setContact(contact);
      form.setVisible(true);
      addClassName("editing");
    }
  }

  private Object getCompanyFormattedName(Contact contact) {
    Company company = contact.getCompany();
    return company == null ? "-" : company.getName();
  }

  private void enableAutoWidth(Grid.Column<Contact> col) {
    col.setAutoWidth(true);
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Search \uD83D\uDD0E");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    Button addContactButton = new Button("Add contact", click -> addContactButton());

    HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
    toolbar.addClassName("toolbar");

    return toolbar;
  }

  private void addContactButton() {
    grid.asSingleSelect().clear();
    editContact(new Contact());
  }
}
