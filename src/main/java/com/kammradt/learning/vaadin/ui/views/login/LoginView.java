package com.kammradt.learning.vaadin.ui.views.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

  LoginForm login = new LoginForm();

  public LoginView() {
    addClassName("login-view");
    setSizeFull();

    setJustifyContentMode(JustifyContentMode.CENTER);
    setAlignItems(Alignment.CENTER);

    login.setAction("login");

    add(new H1("Vaadin CRM"), login);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    if (hasError(beforeEnterEvent)) {
      login.setError(true);
    }
  }

  private boolean hasError(BeforeEnterEvent beforeEnterEvent) {
    return !beforeEnterEvent
        .getLocation()
        .getQueryParameters()
        .getParameters()
        .getOrDefault("error", List.of())
        .isEmpty();
  }
}
