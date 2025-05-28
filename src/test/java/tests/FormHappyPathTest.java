package tests;

import base.Main;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.Form;
import utils.Screenshot;

public class FormHappyPathTest extends Main {

    @Test
    @DisplayName("Envio de formulario exitoso")
    public void testRegistroExitoso() {
        Form form = new Form(driver);

        try {
            test.info("Abriendo la página del formulario.");
            form.open();

            test.info("Completando los datos básicos del usuario.");
            form.fillBasicUserData();

            test.info("Estableciendo fecha de nacimiento: 10 May 2000.");
            form.setDateOfBirth("10 May 2000");

            test.info("Seleccionando materia: Maths.");
            form.setSubject("Maths");

            test.info("Subiendo archivo de prueba: test.png.");
            form.uploadFile("/files/test.png");

            test.info("Seleccionando estado: NCR.");
            form.selectState("NCR");

            test.info("Seleccionando ciudad: Delhi.");
            form.selectCity("Delhi");

            test.info("Enviando formulario.");
            form.submitForm();

            boolean modalVisible = driver.findElements(By.id("example-modal-sizes-title-lg")).size() > 0;

            if (modalVisible) {
                test.pass("Formulario enviado correctamente. Modal de confirmación mostrado.");
                Screenshot.capture(driver, test, "formulario_exitoso");
            } else {
                test.fail("Formulario fue enviado pero no se mostró el modal de confirmación.");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Error durante la ejecución del test: " + e.getMessage());
            Screenshot.capture(driver, test, "registro_fallido");
            throw e;
        }
    }
}
