package com.kammradt.learning.vaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "")
public class MainView extends VerticalLayout {

    public MainView() {
        DatePicker datePicker = new DatePicker("Choose a date!");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.END);

        Button button = new Button("Click!");
        button.addClickListener(e -> clickHandler(datePicker));

        horizontalLayout.add(button, datePicker);
        add(horizontalLayout);
    }

    private void clickHandler(DatePicker datePicker) {
        var date = datePicker.getValue();
        var paragraph = new Paragraph();
        paragraph.setText("Please, select a Date!");
        if (date != null)
           paragraph.setText("Clicked at: " + date);
        add(paragraph);
    }

}
