package com.revature.behaviortest.tests.users;

import com.revature.behaviortest.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class UserRegistrationSteps {
    static WebDriver driver;
    RegistrationPage registrationPage;

    // User Login
    @Given("Planetarium web service is started")
    public void planetarium_web_service_is_started() {
        System.out.println("Planetarium web service is started.");
    }
    @Given("User navigates to the account registration page")
    public void user_navigates_to_the_account_registration_page() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        driver.get("http://localhost:7000/webpage/create");
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);

        // get the page title
        String title = driver.getTitle();
        System.out.println("The page title : "+ title);
    }
    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999);
        registrationPage.registrationInput("validusn"+randomNum, "validpwd");

    }
    @When("Clicks on register button")
    public void clicks_on_register_button() {
        registrationPage.registrationSubmit();
    }
    @Then("User is successfully registered an account")
    public void user_is_successfully_registered_an_account() {
        String alertMessage = registrationPage.registrationAlertDismiss();
        // Account created successfully
        Assert.assertTrue(alertMessage.contains("Account created successfully"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.quit();
    }

    @When("User enters duplicated username and then password")
    public void user_enters_duplicated_username_and_then_password() {
        registrationPage.registrationInput("duplicate", "noMatter");
    }

    @Then("User can not register an account")
    public void user_can_not_register_an_account() {
        String alertMessage = registrationPage.registrationAlertDismiss();
        // Account creation failed
        Assert.assertTrue(alertMessage.contains("Account creation failed "));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.quit();
    }

    @When("User enters blank username or blank password")
    public void user_enters_blank_username_or_blank_password() {
        registrationPage.registrationInput("       ", "        ");
    }

    @When("User enters username or password with more than {int} characters")
    public void user_enters_username_or_password_with_more_than_characters(Integer int1) {
        // 102 chars
        registrationPage.registrationInput(
                "toomuchcharacterstoomuchcharacterstoomuchcharacterstoomuchcharacterstoomuchcharacterstoomuchcharacters",
                "noMatter");
    }

}
