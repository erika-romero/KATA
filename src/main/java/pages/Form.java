package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataGenerator;

import javax.xml.crypto.Data;
import java.time.Duration;

public class Form{
    private WebDriver driver;
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.cssSelector("label[for='gender-radio-1']");
    private By phone = By.id("userNumber");
    private By dob = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbiesCheckbox1 = By.cssSelector("label[for='hobbies-checkbox-1']");
    private By hobbiesCheckbox2 = By.cssSelector("label[for='hobbies-checkbox-2']");
    private By hobbiesCheckbox3 = By.cssSelector("label[for='hobbies-checkbox-3']");
    private By uploadPicture = By.id("uploadPicture");
    private By currentAddress = By.id("currentAddress");
    private By stateInput = By.id("react-select-3-input");
    private By cityInput = By.id("react-select-4-input");
    private By submitButton = By.id("submit");

    public Form(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void setFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    public void setLastName(String lastName) {
        driver.findElement(this.lastName).sendKeys(lastName);
    }

    public void setEmail(String email) {
        WebElement emailField = driver.findElement(this.email);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void selectGenderMale() {
        driver.findElement(genderMale).click();
    }

    public void setPhone(String phone) {
        WebElement phoneinput = driver.findElement(this.phone);
        phoneinput.clear();
        phoneinput.sendKeys(phone);
    }

    public void setDateOfBirth(String dobStr) {
        WebElement dobElement = driver.findElement(dob);
        dobElement.click();
        dobElement.sendKeys(Keys.CONTROL + "a", dobStr, Keys.ENTER);
    }

    public void setSubject(String subject) {
        WebElement subjectInputElement = driver.findElement(subjectsInput);
        subjectInputElement.sendKeys(subject);
        subjectInputElement.sendKeys(Keys.ENTER);
        subjectInputElement.sendKeys(Keys.ESCAPE);
    }

    private void scrollAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void selectHobbies(boolean hobby1, boolean hobby2, boolean hobby3) {

        if (hobby1) scrollAndClick(driver.findElement(hobbiesCheckbox1));
        if (hobby2) scrollAndClick(driver.findElement(hobbiesCheckbox2));
        if (hobby3) scrollAndClick(driver.findElement(hobbiesCheckbox3));
    }

    public void setAddress(String address) {
        driver.findElement(currentAddress).sendKeys(address);
    }


    public void uploadFile(String filePath) {
        String rutaAbsoluta = System.getProperty("user.dir") + "/" + filePath;
        WebElement uploadElement = driver.findElement(uploadPicture);
        uploadElement.sendKeys(rutaAbsoluta);
    }


    public void selectState(String state) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(stateInput));
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);
        stateField.sendKeys(Keys.ESCAPE);
    }

    public void selectCity(String city) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(cityInput));
        stateField.sendKeys(city);
        stateField.sendKeys(Keys.ENTER);
        stateField.sendKeys(Keys.ESCAPE);
    }

    public void submitForm() {
        WebElement submitBtn = driver.findElement(submitButton);
        scrollAndClick(submitBtn);
    }


    public void fillBasicUserData() {
        setFirstName(DataGenerator.getFirstName());
        setLastName(DataGenerator.getLastName());
        setEmail(DataGenerator.getEmail());
        selectGenderMale();
        setPhone(DataGenerator.getPhoneNumber());
        selectHobbies(true,true,true);
        setAddress(DataGenerator.getAddress());
    }
}
