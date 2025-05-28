package tests;

import base.Main;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.Screenshot;
import pages.Form;

public class FormEmptyRequiredFieldsTest extends Main {

    @Test
    @DisplayName("Bloqueo al enviar formulario con campos obligatorios vacíos")
    public void testCamposObligatoriosVacios() {
        Form form = new Form(driver);

        try {
            test.info("Abriendo la página del formulario.");
            form.open();

            test.info("Intentando enviar el formulario sin completar ningún campo obligatorio.");
            form.submitForm();

            test.info("Verificando si se mostró el modal de confirmación.");
            boolean isModalVisible = driver.findElements(By.id("example-modal-sizes-title-lg")).size() > 0;
            WebElement formulario = driver.findElement(By.className("practice-form-wrapper"));

            if (!isModalVisible) {
                test.pass("El formulario fue bloqueado correctamente al dejar campos obligatorios vacíos.");
                form.scrollToElement(formulario);
                Screenshot.capture(driver, test, "bloqueo_campos_vacios");
            } else {
                test.fail("Error: El formulario se envió a pesar de campos vacíos obligatorios.");
                Screenshot.capture(driver, test, "fallo_envio_campos_vacios");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Excepción durante el test: " + e.getMessage());
            Screenshot.capture(driver, test, "excepcion_campos_vacios");
            throw e;
        }
    }
}