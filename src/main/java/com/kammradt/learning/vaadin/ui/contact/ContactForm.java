package com.kammradt.learning.vaadin.ui.contact;

import com.kammradt.learning.vaadin.backend.company.Company;
import com.kammradt.learning.vaadin.backend.contact.Contact;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ContactForm extends FormLayout {

  TextField firstName = new TextField("First name");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  ComboBox<Contact.Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

  public ContactForm(List<Company> companies) {
    addClassName("contact-form");

    binder.bindInstanceFields(this);
    status.setItems(Contact.Status.values());
    company.setItems(companies);
    company.setItemLabelGenerator(Company::getName);

    add(firstName, lastName, email, status, company, createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    save.addClickShortcut(Key.ENTER);
    save.addClickListener(e -> validateAndSave());

    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    delete.addClickListener(e -> delete());

    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    close.addClickShortcut(Key.ESCAPE);
    close.addClickListener(e -> close());

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close);
  }

  private void delete() {
    fireEvent(new ContactFormEvent.DeleteEvent(this,binder.getBean()));
  }

  private void validateAndSave() {
    if (binder.isValid()) {
      fireEvent(new ContactFormEvent.SaveEvent(this, binder.getBean()));
    }
  }

  private void close() {
    fireEvent(new ContactFormEvent.CloseEvent(this));
  }

  public void setContact(Contact contact) {
    binder.setBean(contact);
  }

  public <T extends ComponentEvent<?>> Registration addListener(
      Class<T> eventType, ComponentEventListener<T> listener) { //
    return getEventBus().addListener(eventType, listener);
  }
}
