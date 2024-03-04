package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EnviosPage extends BasePage{

    private By OrigenCampoRazonSocial = By.xpath("//input[@id='envio-origen-nombre']");
    private By OrigenCampoMail = By.xpath("//input[@id='envio-origen-mail-celular']']");
    private By OrigenCampoProvincia = By.xpath("//select[@id='envio-origen-provincia']");
    private By OrigenCampoSucursal = By.xpath("//select[@id='envio-origen-sucursal']");
    private By OrigenBtnSiguiente = By.xpath("//button[@id='btn-siguiente-envios']");
    private By DestinoCheckDomicilio = By.xpath("//input[@id='domicilio']");
    private By DestinoCheckSucursal = By.xpath("//input[@id='sucursal']");
    private By DestinoCampoNomYApellido = By.xpath("//input[@id='envio-destino-nombre']");
    private By DestinoDropdownProvincia = By.xpath("//select[@id='envio-destino-provincia']");
    private By DestinoDropdownCordoba = By.xpath("//*[@id='envio-destino-provincia']/option[7]");
    private By DestinoDropdownLaRioja = By.xpath("//*[@id='envio-destino-provincia']/option[13]");
    private By SucursalDropdownChamical = By.xpath("//*[@id='envio-destino-sucursal']/option[5]");
    private By DestinoCampoLocalidad = By.xpath("//input[@id='envio-destino-localidad']");
    private By DestinoCampoCalle = By.xpath("//input[@id='envio-destino-calle']");
    private By DestinoCampoAltura = By.xpath("//input[@id='envio-destino-altura']");
    private By DestinoCampoCP = By.xpath("//input[@id='envio-destino-cp']");
    private By DestinoCampoMail = By.xpath("//input[@id='envio-destino-mail']");
    private By DestinoBtnSiguiente = By.xpath("(//button[normalize-space()='Siguiente'])[2]");
    private By PaqueteTipoProducto = By.xpath("//select[@id='tipo_producto']");
    private By PaqueteTipoClasico = By.xpath("//option[normalize-space()='PAQ.AR CLASICO']");
    private By PaqueteTipoExpreso = By.xpath("//option[normalize-space()='PAQ.AR EXPRESO']");
    private By PaqueteCampoPeso = By.xpath("//input[@id='envio-peso']");
    private By PaqueteCampoLargo = By.xpath("//input[@id='envio-largo']");
    private By PaqueteCampoAncho = By.xpath("//input[@id='envio-ancho']");
    private By PaqueteCampoAltura = By.xpath("//input[@id='envio-espesor']");
    private By PaqueteCampoValor = By.xpath("//input[@id='envio-valor']");
    private By PaqueteBtnAgregar = By.xpath("//button[@id='btnagregar']");
    public EnviosPage(WebDriver driver){super(driver);}

    public void EnvioIndividualDomicilio(){
        click(OrigenBtnSiguiente);
        waitForSeconds(2);
        click(DestinoCheckDomicilio);
        writeText(DestinoCampoNomYApellido, "Raul Mesa");
        click(DestinoDropdownProvincia);
        waitForSeconds(2);
        click(DestinoDropdownCordoba);
        writeText(DestinoCampoLocalidad,"CORDOBA");
        waitForSeconds(1);
        writeText(DestinoCampoCalle,"San Martin");
        waitForSeconds(1);
        writeText(DestinoCampoAltura,"2455");
        waitForSeconds(1);
        writeText(DestinoCampoCP,"5000");
        waitForSeconds(1);
        writeText(DestinoCampoMail,"navarrete22@yopmail.com");
        waitForSeconds(3);
        clickWithRetry(DestinoBtnSiguiente);
        waitForSeconds(2);
        System.out.println("Formulario de datos del Destino completo!");
    }
    public void EnvioIndividualSucursal(){
        click(OrigenBtnSiguiente);
        waitForSeconds(2);
        click(DestinoCheckSucursal);
        writeText(DestinoCampoNomYApellido, "Carlos Santana");
        click(DestinoDropdownProvincia);
        waitForSeconds(2);
        click(DestinoDropdownLaRioja);
        waitForSeconds(2);
        click(SucursalDropdownChamical);
        waitForSeconds(1);
        writeText(DestinoCampoMail,"danilo@yopmail.com");
        waitForSeconds(1);
        clickWithRetry(DestinoBtnSiguiente);
        waitForSeconds(1);
        System.out.println("Formulario de datos del Destino completo!");
    }


    public void EnvioIndividualDomicilio2(){
        waitForSeconds(2);
        writeText(DestinoCampoNomYApellido, "Raul Mesa");
        click(DestinoDropdownProvincia);
        waitForSeconds(2);
        click(DestinoDropdownCordoba);
        writeText(DestinoCampoLocalidad,"CORDOBA");
        waitForSeconds(1);
        writeText(DestinoCampoCalle,"San Martin");
        waitForSeconds(1);
        writeText(DestinoCampoAltura,"2455");
        waitForSeconds(1);
        writeText(DestinoCampoCP,"5000");
        waitForSeconds(1);
        writeText(DestinoCampoMail,"navarrete22@yopmail.com");
        waitForSeconds(3);
        clickWithRetry(DestinoBtnSiguiente);
        waitForSeconds(2);
        System.out.println("Formulario de datos del Destino completo!");
    }
    public void EnvioIndividualSucursal2(){
        waitForSeconds(2);
        writeText(DestinoCampoNomYApellido, "Carlos Santana");
        click(DestinoDropdownProvincia);
        waitForSeconds(2);
        click(DestinoDropdownLaRioja);
        waitForSeconds(2);
        click(SucursalDropdownChamical);
        waitForSeconds(1);
        writeText(DestinoCampoMail,"danilo@yopmail.com");
        waitForSeconds(1);
        clickWithRetry(DestinoBtnSiguiente);
        waitForSeconds(1);
        System.out.println("Formulario de datos del Destino completo!");
    }
    public void destinoEnvio(String destinoEnvio) {
        switch (destinoEnvio.toLowerCase()) {
            case "a domicilio":
                click(OrigenBtnSiguiente);
                clickWithRetry(DestinoCheckDomicilio);
                EnvioIndividualDomicilio2();
                break;
            case "a sucursal":
                click(OrigenBtnSiguiente);
                clickWithRetry(DestinoCheckSucursal);
                EnvioIndividualSucursal2();
                break;
            default:
                throw new IllegalArgumentException("Tipo de envío no válido: " + destinoEnvio);
        }
    }
    public void DatosDelPaqueteClasico(){
        click(PaqueteTipoProducto);
        waitForElementToBeClickable(PaqueteTipoClasico);
        click(PaqueteTipoClasico);
        waitForSeconds(1);
        writeText(PaqueteCampoPeso,"10");
        waitForSeconds(1);
        writeText(PaqueteCampoLargo,"25");
        waitForSeconds(1);
        writeText(PaqueteCampoAncho,"25");
        waitForSeconds(1);
        writeText(PaqueteCampoAltura,"30");
        waitForSeconds(1);
        writeText(PaqueteCampoValor,"2500");
        waitForSeconds(1);
        System.out.println("Formulario de datos del Paquete completo!");
    }
    public void DatosDelPaqueteExpreso(){
        click(PaqueteTipoProducto);
        waitForElementToBeClickable(PaqueteTipoExpreso);
        click(PaqueteTipoExpreso);
        waitForSeconds(1);
        writeText(PaqueteCampoPeso,"10");
        waitForSeconds(1);
        writeText(PaqueteCampoLargo,"25");
        waitForSeconds(1);
        writeText(PaqueteCampoAncho,"25");
        waitForSeconds(1);
        writeText(PaqueteCampoAltura,"30");
        waitForSeconds(1);
        writeText(PaqueteCampoValor,"2500");
        waitForSeconds(1);
        System.out.println("Formulario de datos del Paquete completo!");
    }

}
