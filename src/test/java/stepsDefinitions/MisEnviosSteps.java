package stepsDefinitions;

import framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.EnviosPage;
import page.FormularioPage;
import page.LoginPage;
import page.MessageHomePage;


public class MisEnviosSteps {
    private WebDriver driver = DriverManager.getDriver();
    private String baseUrl = DriverManager.config.getProperty("url");
    private LoginPage loginPage = new LoginPage(driver);
    private MessageHomePage messageHomePage = new MessageHomePage(driver);
    private EnviosPage enviosPage = new EnviosPage(driver);
    private FormularioPage formularioPage = new FormularioPage(driver);

    @Given("^el usuario se situa en los campos email y password$")
    public void el_usuario_se_situa_en_los_campos_email_y_password() {
        driver.get(baseUrl);
        loginPage.click(By.xpath("//a[contains(text(),'Ingresá')]"));
    }

    @Given("^el usuario '(.*)' está logueado y en la page home$")
    public void el_usuario_está_logueado_y_en_la_page_home(String tipoUsuario) {
        // Lógica para iniciar sesión según el tipo de usuario
        if (tipoUsuario.equals("Consumidor final")) {
            iniciarSesionComoConsumidorFinal();
        } else if (tipoUsuario.equals("Monotributista")) {
            iniciarSesionComoMonotributista();
        } else if (tipoUsuario.equals("Empresa")) {
            iniciarSesionComoEmpresa();
        } else {
            // Tipo de usuario no reconocido, manejar el error apropiadamente
            System.out.println("Tipo de usuario no reconocido: " + tipoUsuario);
            // Aquí podrías lanzar una excepción, mostrar un mensaje de error, etc.
        }
    }
    // Métodos para iniciar sesión según el tipo de usuario
    private void iniciarSesionComoConsumidorFinal() {
        // Lógica para iniciar sesión como consumidor final
        boolean loginExitoso = false;
        String expectedUrl = "https://twsec02.correoargentino.com.ar/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 1
                loginPage.writeText(By.id("email"), "cgoicochea@correoargentino.com.ar");
                loginPage.writeText(By.id("password"), "Pepino23");
                loginPage.click(By.xpath("//button[@title='Si ya tenés usuario y contraseña accedé desde aquí']"));

                // Paso 2
                loginPage.waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = loginPage.getCurrentURL();
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    loginExitoso = true;
                } else {
                    // Si no estamos en la página principal, continuar con el siguiente intento
                    System.out.println("Inicio de sesión fallido. No estamos en la página principal.");
                    intentos++;
                }
            } catch (Exception e) {
                // Si se produce una excepción, incrementar el contador de intentos
                intentos++;
                System.out.println("Intento de inicio de sesión #" + intentos + " fallido.");
            }
        }
    }

    private void iniciarSesionComoMonotributista() {
        // Lógica para iniciar sesión como monotributista
        boolean loginExitoso = false;
        String expectedUrl = "https://twsec02.correoargentino.com.ar/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 1
                loginPage.writeText(By.id("email"), "mono_tester@yopmail.com");
                loginPage.writeText(By.id("password"), "123123");
                loginPage.click(By.xpath("//button[@title='Si ya tenés usuario y contraseña accedé desde aquí']"));

                // Paso 2
                loginPage.waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = loginPage.getCurrentURL();
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    loginExitoso = true;
                } else {
                    // Si no estamos en la página principal, continuar con el siguiente intento
                    System.out.println("Inicio de sesión fallido. No estamos en la página principal.");
                    intentos++;
                }
            } catch (Exception e) {
                // Si se produce una excepción, incrementar el contador de intentos
                intentos++;
                System.out.println("Intento de inicio de sesión #" + intentos + " fallido.");
            }
        }
    }

    private void iniciarSesionComoEmpresa() {
        // Lógica para iniciar sesión como empresa
        boolean loginExitoso = false;
        String expectedUrl = "https://twsec02.correoargentino.com.ar/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 1
                loginPage.writeText(By.id("email"), "empctacte_test@yopmail.com");
                loginPage.writeText(By.id("password"), "123123");
                loginPage.click(By.xpath("//button[@title='Si ya tenés usuario y contraseña accedé desde aquí']"));

                // Paso 2
                loginPage.waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = loginPage.getCurrentURL();
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    loginExitoso = true;
                } else {
                    // Si no estamos en la página principal, continuar con el siguiente intento
                    System.out.println("Inicio de sesión fallido. No estamos en la página principal.");
                    intentos++;
                }
            } catch (Exception e) {
                // Si se produce una excepción, incrementar el contador de intentos
                intentos++;
                System.out.println("Intento de inicio de sesión #" + intentos + " fallido.");
            }
        }
    }

    @And("^llena los campos de origen destino '(.*)' y paquete '(.*)'$")
    public void llenarCamposOrigenDestino(String destinoEnvio, String tipoProducto) {
        if (destinoEnvio.equalsIgnoreCase("Domicilio")) {
            llenarDatosDestinoDomicilio();
        } else if (destinoEnvio.equalsIgnoreCase("Sucursal")) {
            llenarDatosDestinoSucursal();
        } else {
            throw new IllegalArgumentException("Tipo de envío no válido: " + destinoEnvio);
        }

        // Lógica para llenar los campos del tipo de producto
        if (tipoProducto.equalsIgnoreCase("Clasico")) {
            enviosPage.DatosDelPaqueteClasico();
        } else if (tipoProducto.equalsIgnoreCase("Expreso")) {
            enviosPage.DatosDelPaqueteExpreso();
        } else {
            throw new IllegalArgumentException("Tipo de producto no válido: " + tipoProducto);
        }
    }
    private void llenarDatosDestinoDomicilio() {
        enviosPage.click(By.xpath("//button[@id='btn-siguiente-envios']"));
        enviosPage.clickWithRetry(By.xpath("//input[@id='domicilio']"));
        enviosPage.EnvioIndividualDomicilio2();
    }
    private void llenarDatosDestinoSucursal() {
        enviosPage.click(By.xpath("//button[@id='btn-siguiente-envios']"));
        enviosPage.clickWithRetry(By.xpath("//input[@id='sucursal']"));
        enviosPage.EnvioIndividualSucursal2();
    }

    @Then("^realiza el '(.*)' del envío$")
    public void seleccionarMedioPago(String medioPago) {
        if (medioPago.equalsIgnoreCase("tarjeta")) {
            formularioPage.clickWithRetry(By.id("radioTarjeta"));
            pagarConTarjeta();
        } else if (medioPago.equalsIgnoreCase("cuenta corriente")) {
            formularioPage.clickWithRetry(By.id("radioCuentaCorriente"));
            pagarConCtaCte();
        } else if (medioPago.equalsIgnoreCase("saldo")) {
            formularioPage.clickWithRetry(By.id("radioSaldo"));
            pagarConSaldo();
        } else {
            throw new IllegalArgumentException("Medio de pago no válido: " + medioPago);
        }
    }
    private void pagarConTarjeta(){
        formularioPage.waitForElementToBeClickable(By.xpath("//button[@class='btn btn-arrow-right btn-arrow-siguiente']"));
        formularioPage.clickWithRetry(By.xpath("//button[@class='btn btn-arrow-right btn-arrow-siguiente']"));
        formularioPage.waitForSeconds(2);
        formularioPage.assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/spsdecidir/form");
        System.out.println("Estamos en la seccion de datos de la tarjeta");
        formularioPage.validarFormularioTarjeta2();
        formularioPage.clickWithRetry(By.xpath("//label[@title='Visa']"));
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("card_number"),"4507990000004905");
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("security_code"),"775");
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("card_expiration_month"),"08");
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("card_expiration_year"),"25");
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("card_holder_name"),"TARJETA VISA");
        formularioPage.waitForSeconds(1);
        formularioPage.writeText(By.id("card_holder_doc_number"),"27859328");
        formularioPage.waitForSeconds(1);
        formularioPage.clickWithRetry(By.xpath("//button[@id='pagar']"));
        formularioPage.waitForSeconds(4);
    }
    private void pagarConSaldo(){
        formularioPage.clickWithRetry(By.xpath("//button[@class='btn btn-arrow-right btn-arrow-siguiente']"));
        formularioPage.waitForSeconds(2);
        formularioPage.assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/saldo/form");
        formularioPage.clickWithRetry(By.xpath("//button[@id='pagar']"));
        formularioPage.waitForSeconds(4);
    }
    private void pagarConCtaCte(){
        formularioPage.clickWithRetry(By.xpath("//button[@class='btn btn-arrow-right btn-arrow-siguiente']"));
        formularioPage.waitForSeconds(2);
        formularioPage.assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/cuentacorriente/payment");
        formularioPage.clickWithRetry(By.xpath("//button[@id='pagar']"));
        formularioPage.waitForSeconds(4);
    }
}


