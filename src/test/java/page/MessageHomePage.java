package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessageHomePage extends BasePage{
    private By EnvioPaqueteLocator = By.xpath("(//i[@class='caret'])[01]");
    private By NuevoEnvioLocator = By.xpath("//a[contains(text(),'Nuevo envío')]");

    public MessageHomePage(WebDriver driver) {
        super(driver);
    }

    public void SelectEnvioIndividual() {
        assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/message-home");
        waitForElementToBeClickable(EnvioPaqueteLocator);
        click(EnvioPaqueteLocator);
        waitForSeconds(1);
        clickWithRetry(NuevoEnvioLocator);
        assertURL("https://twsec02.correoargentino.com.ar/MiCorreo/public/envio");
        waitForSeconds(1);
        // Si la aserción de URL fue exitosa, imprime un mensaje
        System.out.println("¡Ingresamos a la sección de envíos exitosamente!");
    }
}
