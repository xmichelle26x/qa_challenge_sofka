package com.mishell.demoblaze.stepDefinitions;

import com.mishell.demoblaze.questions.OrderConfirmationMessage;
import com.mishell.demoblaze.tasks.AddProductToCart;
import com.mishell.demoblaze.tasks.ConfirmPurchase;
import com.mishell.demoblaze.tasks.FillOrderForm;
import com.mishell.demoblaze.tasks.ViewCart;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class PurchaseStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver navegador;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el usuario está en la página de inicio de Demoblaze")
    public void openHomePage() {
        Actor usuario = OnStage.theActorCalled("el usuario");
         usuario.can(BrowseTheWeb.with(navegador));
        usuario.attemptsTo(Open.url("https://www.demoblaze.com/"));
    }

   @When("el usuario agrega {string} y {string} al carrito")
    public void addTwoProducts(String producto1, String producto2) {
        Actor usuario = OnStage.theActorInTheSpotlight();
        usuario.attemptsTo(
            AddProductToCart.named(producto1),
            AddProductToCart.named(producto2)
    );
}

    @When("el usuario visualiza el carrito")
    public void viewCart() {
        OnStage.theActorInTheSpotlight().attemptsTo(ViewCart.now());
    }

    @When("el usuario completa el formulario de compra con:")
    public void fillForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        OnStage.theActorInTheSpotlight().attemptsTo(
            FillOrderForm.with()
                .name(data.get("name"))
                .country(data.get("country"))
                .city(data.get("city"))
                .card(data.get("card"))
                .month(data.get("month"))
                .year(data.get("year"))
                .build()
        );
    }

    @Then("el usuario debería ver el mensaje {string}")
    public void verifyMessage(String expectedMessage) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(OrderConfirmationMessage.displayed(), containsString(expectedMessage))
        );
    }

    @Then("el usuario cierra el modal de confirmación")
    public void closeModal() {
        OnStage.theActorInTheSpotlight().attemptsTo(ConfirmPurchase.andCloseTheModal());
    }
}