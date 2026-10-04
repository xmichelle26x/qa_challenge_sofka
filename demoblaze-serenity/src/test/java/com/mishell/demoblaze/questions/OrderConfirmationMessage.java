package com.mishell.demoblaze.questions;

import com.mishell.demoblaze.ui.OrderFormUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OrderConfirmationMessage implements Question<String> {

    public static OrderConfirmationMessage displayed() {
        return new OrderConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(OrderFormUI.CONFIRMATION_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
        return Text.of(OrderFormUI.CONFIRMATION_TITLE).answeredBy(actor);
    }
}