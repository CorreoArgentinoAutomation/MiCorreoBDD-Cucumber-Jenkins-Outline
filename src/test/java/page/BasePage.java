package page;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public void clickWithRetry(By locator) {
        int maxAttempts = 3; // Número máximo de intentos
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                click(locator);
                return; // Salir del método si la operación de clic tiene éxito
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                // Manejar la excepción (puede agregar un registro o tomar una captura de pantalla aquí)
                System.err.println("Error al hacer clic en el elemento: " + e.getMessage());
                // Esperar antes de intentar nuevamente
                waitForSeconds(1);
            }
        }
        // Si todos los intentos fallan, lanzar la excepción
        throw new ElementNotInteractableException("No se pudo interactuar con el elemento después de " + maxAttempts + " intentos");
    }

    public void moveToElementAndClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = findElement(locator);
        actions.moveToElement(element).click().perform();
    }
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void assertURL(String expectedUrl) {
        String currentUrl = getCurrentURL();
        Assert.assertEquals("La URL actual no coincide con la URL esperada", expectedUrl, currentUrl);
    }

    public boolean waitForUrlToBe(String url, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            return false; // El tiempo de espera se agotó antes de que la URL coincidiera
        }
    }

    public void writeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText().trim();
    }
    public boolean compararTextoConMensajeEsperado(By locator, String textoEsperado) {
        // Obtener el texto del sitio utilizando el localizador proporcionado
        String textoDelSitio = getText(locator);
        // Comparar el texto del sitio con el texto esperado
        return textoDelSitio.equals(textoEsperado);
    }
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    private WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void validarCampo(String nombreCampo, By locator, String textoEsperado) {
        String textoCampo = getText(locator); // Utiliza el método getText() de BasePage para obtener el texto del elemento
        // Verifica si el texto del campo coincide con el texto esperado
        if (textoCampo.equals(textoEsperado)) {
            System.out.println("El campo '" + nombreCampo + "' está correctamente llenado: " + textoCampo);
        } else {
            System.out.println("El campo '" + nombreCampo + "' no coincide con el texto esperado.");
            System.out.println("Texto esperado: " + textoEsperado);
            System.out.println("Texto actual: " + textoCampo);
        }
    }
    public boolean validarCampoExistenteYEditable(By locator) {
        try {
            // Buscar el elemento por el locator proporcionado
            WebElement element = findElement(locator);

            // Validar que el elemento existe
            Assert.assertTrue(element.isDisplayed());

            // Validar que el elemento es editable (en este caso, solo para campos de texto)
            Assert.assertTrue(element.isEnabled());

            // Si se llega a este punto, la validación fue exitosa
            return true;
        } catch (Exception e) {
            // En caso de cualquier excepción, capturar y mostrar el mensaje de error
            e.printStackTrace();
            System.out.println("Error al validar el campo: " + e.getMessage());
            // La validación no fue exitosa
            return false;
        }
    }

}
