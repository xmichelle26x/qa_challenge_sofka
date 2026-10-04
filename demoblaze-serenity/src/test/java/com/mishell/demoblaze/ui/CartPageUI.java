package com.mishell.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPageUI {

    public static final Target PLACE_ORDER_BUTTON =
        Target.the("Botón 'Place Order'")
              .locatedBy("//button[text()='Place Order']");

    public static final Target CART_ROWS =
        Target.the("Filas de productos en el carrito")
              .locatedBy("#tbodyid tr");
}