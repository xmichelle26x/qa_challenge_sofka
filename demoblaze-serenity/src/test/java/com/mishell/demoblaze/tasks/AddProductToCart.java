package com.mishell.demoblaze.tasks;

import com.mishell.demoblaze.ui.HomePageUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return instrumented(AddProductToCart.class, productName);
    }

    @Override
    @Step("{0} agrega el producto '#productName' al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(HomePageUI.PRODUCT_LINK.of(productName)),
            WaitUntil.the(HomePageUI.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
            Click.on(HomePageUI.ADD_TO_CART_BUTTON),
            Switch.toAlert().andAccept(),
            OpenHomePage.demoblaze()
        );
    }
}