package com.revature.behaviortest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath="//input[@id='usernameInput']")
    WebElement username;

    @FindBy(xpath="//input[@id='passwordInput']")
    WebElement password;

    public void login(String uname, String pwd){
        username.sendKeys(uname);
        password.sendKeys(pwd);
    }
}
