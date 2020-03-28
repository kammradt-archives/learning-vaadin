package com.kammradt.learning.vaadin.security;

import com.kammradt.learning.vaadin.ui.views.login.LoginView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

  @Override
  public void serviceInit(ServiceInitEvent event) {
    event.getSource().addUIInitListener(e -> e.getUI().addBeforeEnterListener(this::beforeEnter));
  }

  private void beforeEnter(BeforeEnterEvent event) {
    if (!isLoginPage(event) && !SecurityUtils.isUserLoggedIn()) {
      event.rerouteTo(LoginView.class);
    }
  }

  private boolean isLoginPage(BeforeEnterEvent event) {
    return LoginView.class.equals(event.getNavigationTarget());
  }
}
