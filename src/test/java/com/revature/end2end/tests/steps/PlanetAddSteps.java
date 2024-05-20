package com.revature.end2end.tests.steps;

import com.revature.end2end.pages.HomePage;
import com.revature.end2end.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.ThreadLocalRandom;

public class PlanetAddSteps {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Given("Planetarium web service is started")
    public void planetarium_web_service_is_started() {
        System.out.println("Planetarium web service is started.");
    }
    @Given("User navigates to the Planetarium home page")
    public void user_navigates_to_the_planetarium_home_page() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);

        // login first
        loginPage.naviageToPage();
        loginPage.loginInput("test", "test");
        loginPage.loginSubmit();


    }

    @When("User selects the {string} from dropdown menu")
    public void user_selects_the_from_dropdown_menu(String option) {
        homePage.setSelect(option);
    }

    @When("enters {string} planet name")
    public void enters_planet_name(String planet) {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999);
        if(planet.equals("duplicated"))
            homePage.addPlanet(planet);
        else
            homePage.addPlanet(planet+randomNum);
    }

    @When("Clicks on submit {string} button")
    public void clicks_on_submit_button(String string) {
        homePage.addSubmit();
    }

    @Then("User successfully adds a unique planet {string}")
    public void user_successfully_adds_a_unique_planet(String planet) {
        Assert.assertTrue(homePage.foundPlanet(planet));
        homePage.closePage();
    }

    @Then("User can not add the planet {string}")
    public void user_can_not_add_the_planet(String planet) {
        if(planet.equals("duplicated"))
            Assert.assertTrue(homePage.foundPlanet(planet));
        else Assert.assertFalse(homePage.foundPlanet(planet));
        homePage.closePage();
    }


}
