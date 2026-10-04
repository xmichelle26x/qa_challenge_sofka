package com.mishell.demoblaze.tasks;

import com.mishell.demoblaze.ui.OrderFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConfirmPurchase implements Task {

    public static ConfirmPurchase andCloseTheModal() {
        return instrumented(ConfirmPurchase.class);
    }

    @Override
    @Step("{0} cierra el modal de confirmación")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(OrderFormUI.CONFIRMATION_OK_BUTTON));
    }
}