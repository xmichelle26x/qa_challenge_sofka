package com.mishell.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePageUI {

    public static final Target PRODUCT_LINK =
        Target.the("Enlace del producto {0}")
              .locatedBy("//a[normalize-space()='{0}']");

    public static final Target ADD_TO_CART_BUTTON = 
        Target.the("Botón 'Add to cart'")
              .locatedBy("//a[text()='Add to cart']");


    public static final Target CART_LINK =
        Target.the("Enlace del carrito")
              .locatedBy("#cartur");
}