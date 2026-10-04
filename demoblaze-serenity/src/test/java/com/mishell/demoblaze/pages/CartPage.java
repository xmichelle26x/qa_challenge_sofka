package com.mishell.demoblaze.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    public void clickPlaceOrder() {
        $(By.xpath("//button[text()='Place Order']")).click();
    }
}