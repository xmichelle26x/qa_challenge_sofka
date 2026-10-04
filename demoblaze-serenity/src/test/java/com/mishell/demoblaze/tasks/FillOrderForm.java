package com.mishell.demoblaze.tasks;

import com.mishell.demoblaze.ui.CartPageUI;
import com.mishell.demoblaze.ui.OrderFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillOrderForm implements Task {

    private final String name;
    private final String country;
    private final String city;
    private final String card;
    private final String month;
    private final String year;

    public FillOrderForm(String name, String country, String city,
                         String card, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.card = card;
        this.month = month;
        this.year = year;
    }

    public static OrderFormBuilder with() {
        return new OrderFormBuilder();
    }

    public static class OrderFormBuilder {
        private String name, country, city, card, month, year;

        public OrderFormBuilder name(String name)         { this.name = name; return this; }
        public OrderFormBuilder country(String country)   { this.country = country; return this; }
        public OrderFormBuilder city(String city)         { this.city = city; return this; }
        public OrderFormBuilder card(String card)         { this.card = card; return this; }
        public OrderFormBuilder month(String month)       { this.month = month; return this; }
        public OrderFormBuilder year(String year)         { this.year = year; return this; }

        public FillOrderForm build() {
            return instrumented(FillOrderForm.class, name, country, city, card, month, year);
        }
    }

    @Override
    @Step("{0} completa el formulario de compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CartPageUI.PLACE_ORDER_BUTTON),
            WaitUntil.the(OrderFormUI.NAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
            Enter.theValue(name).into(OrderFormUI.NAME_FIELD),
            Enter.theValue(country).into(OrderFormUI.COUNTRY_FIELD),
            Enter.theValue(city).into(OrderFormUI.CITY_FIELD),
            Enter.theValue(card).into(OrderFormUI.CARD_FIELD),
            Enter.theValue(month).into(OrderFormUI.MONTH_FIELD),
            Enter.theValue(year).into(OrderFormUI.YEAR_FIELD),
            Click.on(OrderFormUI.PURCHASE_BUTTON)
        );
    }
}