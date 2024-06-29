package com.revature.end2end.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;

public class LoginPage {

    WebDriver driver;
//    WebElement username;
//    WebElement password;
//    WebElement loginButton;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//input[@id='usernameInput']")
    WebElement username;
    @FindBy(xpath="//input[@id='passwordInput']")
    WebElement password;
    @FindBy(xpath="//input[@value='Login']")
    WebElement loginButton;

    public void naviageToPage(){
        driver.get("http://localhost:7000/webpage/login");
        driver.manage().window().maximize();
//        username = driver.findElement(By.xpath("//input[@id='usernameInput']"));
//        password = driver.findElement(By.xpath("//input[@id='passwordInput']"));;
//        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));;
        // get the page title
        String title = driver.getTitle();
        System.out.println("The page title : "+ title);

    }
    public void loginInput(String uname, String pwd){
        username.sendKeys(uname);
        password.sendKeys(pwd);
        System.out.printf("username=%s, password=%s.\n", uname, pwd);
    }
    public void loginSubmit(){
        loginButton.click();
    }
    public String loginSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://localhost:7000/api/webpage/home"));
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
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        driver.quit();

    }
}
