package tests;

import base.Main;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.Form;
import utils.Screenshot;

public class FormInvalidPhoneTest extends Main {

    @Test
    @DisplayName("Validación de teléfono inválido")
    public void testTelefonoInvalido() {
        try {
            Form form = new Form(driver);
            form.open();

            test.info("Completando datos básicos del usuario.");
            form.fillBasicUserData();

            test.info("Sobrescribiendo teléfono con valor inválido: 12345");
            form.setPhone("12345");

            test.info("Enviando formulario.");
            form.submitForm();

            WebElement phoneInput = driver.findElement(By.id("userNumber"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean esInvalido = (Boolean) js.executeScript("return arguments[0].matches(':invalid');", phoneInput);

            if (esInvalido) {
                test.pass("El teléfono es inválido según la validación.");
                form.scrollToElement(phoneInput);
                Screenshot.capture(driver, test, "telefono_fallido");
            } else {
                test.fail("El teléfono NO fue marcado como inválido por la validación.");
            }

            boolean modalPresente = driver.findElements(By.id("example-modal-sizes-title-lg")).size() > 0;

            if (modalPresente) {
                test.fail("El formulario fue enviado a pesar del teléfono inválido (modal detectado).");
            } else {
                test.pass("El formulario NO fue enviado debido al teléfono inválido (no se mostró el modal).");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Error durante la ejecución del test: " + e.getMessage());
            Screenshot.capture(driver, test, "telefono_fallido");
            throw e;
        }
    }
}