package com.prueba.automatizada.stepdefinitions;

import com.prueba.automatizada.execution.FormBuscarProducto;
import com.prueba.automatizada.execution.FormCarrito;
import com.prueba.automatizada.page.NavegarPagina;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static net.serenitybdd.screenplay.actors.OnStage.*;


public class comprasStepDefinition {

    private WebDriver driver;
    private Actor actor;


    @Before
    public void setTheStage() {

        /System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Configurar Serenity BDD para usar el WebDriver
        actor2 = Actor.named("Usuario");
        actor2.can(BrowseTheWeb.with(driver));

        // Configurar el escenario de Serenity BDD
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Usuario").can(BrowseTheWeb.with(driver));
    }

    @Given("^(.*) navega a la pagina principal")
    public void navegarALaPaginaPrincipal(String actor) {
        theActorCalled(actor).attemptsTo(NavegarPagina.OpenHomePage());
    }

    @When("Buscar {string}")
    public void buscarTouch(String producto) {
        withCurrentActor(
                FormBuscarProducto.buscarProducto(producto)
        );
    }
    @And("Agregar al carrito")
    public void agregarTouch() {
        withCurrentActor(
                FormBuscarProducto.agregarProducto()
        );
    }

    @When("Click en shopping cart")
    public void botonCarrito(){
        withCurrentActor(
                FormCarrito.botonVerCarrito()
        );
    }
    @And("Verificar productos agregados")
    public void verificarProductos() throws InterruptedException {
        Target spanTotal = Target.the("total carrito").located(By.id("cart-total"));
        Thread.sleep(2000);
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(spanTotal).text().contains("2 item")
        );

    }

    @When("Click en boton checkout")
    public void botonCheckout(){
        withCurrentActor(
                FormCarrito.botonCheckout()
        );
    }

    @And("Seleccionar guest checkout")
    public void seleccionarGuestCheckout() {
        withCurrentActor(
                FormCarrito.guestCheckout()
        );
    }
    /*Se realiza el seleccionar check y clic en boton en un solo proceso*/
    @When("Completar formulario {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void completeCheckoutForm(String firstname, String lastname, String email, String telephone, String address, String city, String postcode, String county, String zone){
        withCurrentActor(
                FormCarrito.completarForm(firstname, lastname, email, telephone, address, city, postcode, county, zone)
        );
    }

    @And("Click boton continue")
    public void clickContinue() {
        withCurrentActor(
                FormCarrito.botonContinueForm(),
                FormCarrito.botonContinueDelivery()
        );
    }

    @When("Marcar Aceptar Terminos y Condiciones")
    public void acceptTermsConditions() {
        withCurrentActor(
                FormCarrito.termsAndConditions()
        );
    }
    @And("Click en boton Continue")
    public void clickContinuePayment() {
        withCurrentActor(
                FormCarrito.botonContinuePayment()
        );
    }

    @When("Validar productos en la orden generada")
    public void validarProductos() throws InterruptedException {
        Target spanTotal = Target.the("total carrito").located(By.id("cart-total"));
        Thread.sleep(2000);
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(spanTotal).text().contains("2 item")
        );
    }

    @And("Click en boton Confirmar Compra")
    public void clickOnShop()  {
        theActorInTheSpotlight().attemptsTo(
                FormCarrito.confirmOrder()
        );
    }

    @Then("Validar mensaje {string}")
    public void validarMensaje(String message) throws InterruptedException {
        Target confirmOrder = Target.the("Mensaje").located(By.xpath("//div[@id='content']/h1"));
        Thread.sleep(2000);
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(confirmOrder).text().contains(message)
        );
    }
}
