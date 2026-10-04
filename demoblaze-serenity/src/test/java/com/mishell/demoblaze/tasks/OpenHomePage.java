package com.mishell.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenHomePage implements Task {

    private static final String URL = "https://www.demoblaze.com/";

    public static OpenHomePage demoblaze() {
        return instrumented(OpenHomePage.class);
    }

    @Override
    @Step("{0} abre la página de Demoblaze")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(URL));
    }
}