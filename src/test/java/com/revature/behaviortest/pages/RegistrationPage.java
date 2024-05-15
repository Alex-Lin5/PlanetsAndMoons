package com.revature.behaviortest.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
//    WebElement username;
//    WebElement password;
//    WebElement registrationButton;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, RegistrationPage.class);
        driver.get("http://localhost:7000/webpage/create");
        driver.manage().window().maximize();
//        username = driver.findElement(By.xpath("//input[@id='usernameInput']"));
//        password = driver.findElement(By.xpath("//input[@id='passwordInput']"));;
//        registrationButton = driver.findElement(By.xpath("//input[@value='Create']"));;
        // get the page title
        String title = driver.getTitle();
        System.out.println("The page title : "+ title);
    }

//    By username = By.xpath("//input[@id='usernameInput']");
//    By password = By.xpath("//input[@id='passwordInput']");

    @FindBy(xpath="//input[@id='usernameInput']")
    WebElement username;
    @FindBy(xpath="//input[@id='passwordInput']")
    WebElement password;
    @FindBy(xpath="//input[@value='Create']")
    WebElement registrationButton;


    public void registrationInput(String uname, String pwd){
        username.sendKeys(uname);
        password.sendKeys(pwd);
        System.out.printf("username=%s, password=%s.\n", uname, pwd);
    }
    public void registrationSubmit(){
        registrationButton.click();
    }
    public String registrationAlertDismiss(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();

        System.out.println(alertMessage);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.quit();
        return  alertMessage;
    }

}
