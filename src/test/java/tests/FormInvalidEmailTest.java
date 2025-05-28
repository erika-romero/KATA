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

public class FormInvalidEmailTest extends Main {

    @Test
    @DisplayName("Validación de correo inválido")
    public void testCorreoInvalido() throws InterruptedException {
        try {
            Form form = new Form(driver);
            test.info("Abriendo el formulario.");
            form.open();
            test.info("Completando datos básicos del usuario.");
            form.fillBasicUserData();
            test.info("Sobrescribiendo el email con uno inválido: correo.invalido.com");
            form.setEmail("correo.invalido.com");
            test.info("Enviando formulario.");
            form.submitForm();
            test.info("Verificando que el campo de email tenga clase de error visual.");

            WebElement emailField = driver.findElement(By.id("userEmail"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            boolean esInvalido = (Boolean) js.executeScript("return arguments[0].matches(':invalid');", emailField);
            if (esInvalido) {
                test.pass("El email ingresado es inválido según la validación");
                form.scrollToElement(emailField);
                Screenshot.capture(driver, test, "email_invalido");
            } else {
                test.fail("El navegador no detectó el email como inválido.");
            }

            boolean modalPresente = driver.findElements(By.id("example-modal-sizes-title-lg")).size() > 0;
            if (modalPresente) {
                test.fail("El formulario fue enviado a pesar del email inválido (modal detectado).");
            } else {
                test.pass("El formulario NO fue enviado debido al email inválido (no se mostró el modal).");
            }


        } catch (Exception e) {
            test.log(Status.FAIL, "Error durante la ejecución del test: " + e.getMessage());
            Screenshot.capture(driver, test, "email_fallido");
            throw e;
        }
    }
}

