package com.revature.end2end.tests.steps;

import com.revature.end2end.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

public class UserLoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    // User login
    @Given("User navigates to the account login page")
    public void user_navigates_to_the_account_login_page() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.naviageToPage();
//        loginPage = new LoginPage(driver);
    }

    @When("User enters valid username and password for login")
    public void user_enters_valid_username_and_password_for_login() {
//        loginPage.loginInput("unique", "valid");
        loginPage.loginInput("test", "test");
    }

    @When("Clicks on login button")
    public void clicks_on_login_button() {
        loginPage.loginSubmit();
    }

    @Then("User is successfully logged in with the account")
    public void user_is_successfully_logged_in_with_the_account() {
        String title = loginPage.loginSuccess();
        Assert.assertTrue(title.contains("Home"));
        loginPage.closePage();
    }

    @When("User enters correct username but wrong password")
    public void user_enters_correct_username_but_wrong_password() {
        loginPage.loginInput("unique", "invalidWrong");
    }

    @Then("User can not login the account")
    public void user_can_not_login_the_account() {
        String alertMessage = loginPage.loginNotSuccess();
        loginPage.closePage();
        Assert.assertEquals(alertMessage, "login attempt failed: please try again");
    }

    @When("User enters non-existent username username")
    public void user_enters_non_existent_username_username() {
        loginPage.loginInput("nonex", "valid");
    }

    @When("User enters blank username or blank password for login")
    public void user_enters_blank_username_or_blank_password_for_login() {
        loginPage.loginInput("       ", "     ");
    }

}
