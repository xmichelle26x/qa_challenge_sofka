package com.mishell.demoblaze.questions;

import com.mishell.demoblaze.ui.CartPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class CartItemCount implements Question<Boolean> {

    public static CartItemCount containsItems() {
        return new CartItemCount();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CartPageUI.CART_ROWS).answeredBy(actor);
    }
}