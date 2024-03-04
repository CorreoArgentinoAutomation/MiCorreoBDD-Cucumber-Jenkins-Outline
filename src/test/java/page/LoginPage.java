package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage extends BasePage {
    private By enterBtn = By.xpath("//a[contains(text(),'Ingresá')]");
    private By emailLocator = By.id("email");
    private By passwordLocator = By.id("password");
    private By btnLogin = By.xpath("//button[@title='Si ya tenés usuario y contraseña accedé desde aquí']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        clickWithRetry(enterBtn);
        boolean loginExitoso = false;
        String expectedUrl = "https://twsec02.correoargentino.com.ar/MiCorreo/public/message-home";
        int intentos = 0;
        int maxIntentos = 2; // Establece el número máximo de intentos

        while (!loginExitoso && intentos < maxIntentos) {
            try {
                // Paso 2
                writeText(emailLocator, "cgoicochea@correoargentino.com.ar");
                writeText(passwordLocator, "Pepino23");
                click(btnLogin);

                // Paso 3
                waitForUrlToBe(expectedUrl, 2);

                // Verificar si la URL es la esperada
                String currentUrl = getCurrentURL(); // Utilizando la función encapsulada
                if (currentUrl.equals(expectedUrl)) {
                    // Si estamos en la página principal, el inicio de sesión es exitoso
                    System.out.println("¡Inicio de sesión exitoso!");
                    System.out.println("Estamos en la página principal (message-home).");
                    return; // Salir del método después de un inicio de sesión exitoso
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
        System.out.println("Inicio de sesión fallido después de " + maxIntentos + " intentos.");
    }


    public void loginOutline(String tipoUsuario) {
        String email;
        String password = "123123"; // Contraseña común para todos los tipos de usuario

        if (tipoUsuario.equals("consumidor final")) {
            email = "cf_tester02@yopmail.com";
        } else if (tipoUsuario.equals("monotributista")) {
            email = "mono_tester@yopmail.com";
        } else if (tipoUsuario.equals("empresa")) {
            email = "empctacte_test@yopmail.com";
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }

        // Llenar campos de email y contraseña con las credenciales específicas
        writeText(emailLocator, email);
        writeText(passwordLocator, password);
        click(btnLogin);
    }



    public void logout(){
        click(By.id("liusuariolink"));
        waitForSeconds(2);
        clickWithRetry(By.xpath("//a[normalize-space()='Cerrar sesión']"));
    }
}



