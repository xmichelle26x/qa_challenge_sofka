package com.mishell.demoblaze.tasks;

import com.mishell.demoblaze.ui.HomePageUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ViewCart implements Task {

    public static ViewCart now() {
        return instrumented(ViewCart.class);
    }

    @Override
    @Step("{0} abre el carrito de compras")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HomePageUI.CART_LINK));
    }
}