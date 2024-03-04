package stepsDefinitions;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.EnviosPage;
import page.FormularioPage;
import page.LoginPage;
import page.MessageHomePage;


public class NuevoEnvioIndividualSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("url");
    private LoginPage loginPage = new LoginPage(driver);
    private MessageHomePage messageHomePage = new MessageHomePage(driver);
    private EnviosPage enviosPage = new EnviosPage(driver);
    private FormularioPage formularioPage = new FormularioPage(driver);


    @Given("el usuario está logueado y en la page home")
    public void browserInit(){
        driver.get(baseUrl);
        loginPage.login();
    }

    @When("ingresa en nuevo envío individual")
    public void newPackageSingle() throws InterruptedException {
        messageHomePage.SelectEnvioIndividual();
    }

    @And("llena los campos de origen destino y paquete")
    public void fillHomePackageForms() throws InterruptedException {
        enviosPage.EnvioIndividualDomicilio();
        enviosPage.DatosDelPaqueteClasico();
    }
    @And("presiona en agregar envío")
    public void addNewPackage() {
        loginPage.clickWithRetry(By.xpath("//button[@id='btnagregar']"));
    }

    @And("el envío se muestra en la grilla de envíos pendientes")
    public void newPackageAddCheck() {
        formularioPage.validarFormularioEnvios();
    }

    @And("presiona en cotizar")
    public void presiona_en_cotizar() {
        formularioPage.cotizar();

    }
    @And("se muestra la grilla de checkout")
    public void se_muestra_la_grilla_de_checkout() {
        //formularioPage.assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/checkout");
        formularioPage.validarFormularioCheckout();

    }

    @Then("realiza el pago del envío")
    public void realiza_el_pago_del_envío() {
        formularioPage.pagarConTarjeta();
    }

    @And("se confirma que el pago se ha realizado con éxito")
    public void se_confirma_que_el_pago_se_ha_realizado_con_éxito() {
        formularioPage.verificarMensajeDeExito();
        formularioPage.mostrarCodigoTNEnvio();
    }

}