package com.revature.behaviortest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:7000/api/webpage/home");
        driver.manage().window().maximize();
        // get the page title
        String title = driver.getTitle();
        System.out.println("The page title : "+ title);
    }
    @FindBy(xpath="//input[@id='usernameInput']")
    WebElement selectPlanet;
    @FindBy(xpath="//input[@id='passwordInput']")
    WebElement addPlanet;
    @FindBy(xpath="//input[@value='Login']")
    WebElement submitButton;

    public void addPlanet(String planet){
        System.out.printf("add planet %s\n", planet);
    }
    public void addSubmit(){
        submitButton.click();
    }
    public String loginSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("home"));
        String title = driver.getTitle();
        System.out.println("Redirected to "+ title);
        return title;
    }
    public String loginNotSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();
        return alertMessage;
    }
    public void closePage(){
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.quit();

    }
}
