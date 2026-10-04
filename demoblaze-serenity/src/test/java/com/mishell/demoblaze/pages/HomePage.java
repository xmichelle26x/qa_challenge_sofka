package com.mishell.demoblaze.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends PageObject {

    @Step("Abrir la página principal")
    public void openHomePage() {
        openUrl("https://www.demoblaze.com/");
    }

    @Step("Agregar el primer producto al carrito")
    public void addFirstProductToCart() {
        $(By.linkText("Samsung galaxy s6")).click();
        $(By.xpath("//a[text()='Add to cart']")).click();
        acceptAlert();
        getDriver().navigate().back();
    }

    @Step("Agregar el segundo producto al carrito")
    public void addSecondProductToCart() {
        $(By.linkText("Nokia lumia 1520")).click();
        $(By.xpath("//a[text()='Add to cart']")).click();
        acceptAlert();
        getDriver().navigate().back();
    }

    @Step("Ir al carrito de compras")
    public void goToCart() {
        $(By.id("cartur")).click();
    }

    // Método helper para esperar y aceptar la alerta
    private void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }
}