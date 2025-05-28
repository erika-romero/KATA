package tests;

import base.Main;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Form;
import utils.Screenshot;

import java.time.Duration;

public class FormDynamicDropdownTest extends Main {

    @Test
    @DisplayName("Validar ciudad habilitada")
    public void testDependenciaEstadoCiudad() {
        try {
            Form form = new Form(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            test.info("Abriendo el formulario.");
            form.open();

            test.info("Seleccionando el estado: NCR.");
            form.selectState("NCR");

            test.info("Esperando que el input de ciudad se habilite después de seleccionar estado.");
            WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));

            test.info("Seleccionando ciudad: Delhi.");
            form.selectCity("Delhi");
            Screenshot.capture(driver,test,"Ciudad Seleccionada");

        } catch (Exception e) {
            test.log(Status.FAIL, "Error durante la prueba de dependencia Estado-Ciudad: " + e.getMessage());
            throw e;
        }
    }
}
