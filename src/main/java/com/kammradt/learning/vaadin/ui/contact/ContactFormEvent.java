package com.kammradt.learning.vaadin.ui.contact;

import com.kammradt.learning.vaadin.backend.contact.Contact;
import com.vaadin.flow.component.ComponentEvent;

public abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
  private Contact contact;

  protected ContactFormEvent(ContactForm source, Contact contact) {
    super(source, false);
    this.contact = contact;
  }

  public Contact getContact() {
    return contact;
  }

  public static class SaveEvent extends ContactFormEvent {
    SaveEvent(ContactForm source, Contact contact) {
      super(source, contact);
    }
  }

  public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(ContactForm source, Contact contact) {
      super(source, contact);
    }
  }

  public static class CloseEvent extends ContactFormEvent {
    CloseEvent(ContactForm source) {
      super(source, null);
    }
  }
}
