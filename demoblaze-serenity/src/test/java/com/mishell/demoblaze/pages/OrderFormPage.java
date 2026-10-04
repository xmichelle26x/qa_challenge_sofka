package com.mishell.demoblaze.pages;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage extends PageObject {

    @Step("Llenar el formulario de compra")
    public void fillForm(String name, String country, String city, String card, String month, String year) {
        $(By.id("name")).type(name);
        $(By.id("country")).type(country);
        $(By.id("city")).type(city);
        $(By.id("card")).type(card);
        $(By.id("month")).type(month);
        $(By.id("year")).type(year);
    }

    @Step("Confirmar la compra")
    public void confirmPurchase() {
        $(By.xpath("//button[text()='Purchase']")).click();
    }

    @Step("Obtener el mensaje de confirmación del modal")
    public String getConfirmationMessage() {
        // Esperar a que el modal de SweetAlert esté visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        // El modal tiene la clase 'sweet-alert' y contiene un <h2> con el mensaje
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert h2")));
        return $(By.cssSelector(".sweet-alert h2")).getText();
    }

    @Step("Cerrar el modal de confirmación")
    public void closeConfirmationModal() {
        // El botón "OK" del modal de SweetAlert tiene la clase 'confirm'
        $(By.cssSelector(".sweet-alert .confirm")).click();
    }
}