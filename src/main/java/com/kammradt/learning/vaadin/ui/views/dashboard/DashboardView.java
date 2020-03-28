package com.kammradt.learning.vaadin.ui.views.dashboard;

import com.kammradt.learning.vaadin.backend.company.CompanyService;
import com.kammradt.learning.vaadin.backend.contact.ContactService;
import com.kammradt.learning.vaadin.ui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Map;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout {

  private final ContactService contactService;
  private final CompanyService companyService;

  public DashboardView(ContactService contactService, CompanyService companyService) {
    this.contactService = contactService;
    this.companyService = companyService;

    addClassName("dashboad-view");
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    add(getContactStats(), getCompaniesChart());
  }

  private Span getContactStats() {
    Span stats = new Span(contactService.findAll().size() + " Contacts");
    stats.addClassName("contact-stats");

    return stats;
  }

  private Component getCompaniesChart() {
    Chart chart = new Chart(ChartType.PIE);

    DataSeries dataSeries = new DataSeries();
    Map<String, Integer> stats = companyService.getStats();
    stats.forEach((name, quantity) -> dataSeries.add(new DataSeriesItem(name, quantity)));

    chart.getConfiguration().setSeries(dataSeries);
    return chart;
  }
}
