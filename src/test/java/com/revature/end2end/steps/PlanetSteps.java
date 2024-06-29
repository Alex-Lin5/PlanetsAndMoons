package com.revature.end2end.steps;

import com.revature.end2end.pages.HomePage;
import com.revature.end2end.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.ThreadLocalRandom;

public class PlanetSteps {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
//    @Before
//    public void setupClass(){
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        homePage = new HomePage(driver);
//        loginPage = new LoginPage(driver);
//    }
//    @After
//    public void teardownClass(){
//        driver.quit();
//    }
    @Given("User navigates to the Planetarium home page")
    public void user_navigates_to_the_planetarium_home_page() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

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


    @Given("User found a valid Planet in home page")
    public void user_found_a_valid_planet_in_home_page() {
//        planetPassId = "0";
    }

    @When("enters planet ID {string} to be deleted")
    public void enters_planet_ID_to_be_deleted(String id) {
        homePage.deletePlanetById(id);
    }

    @When("Clicks on delete button")
    public void clicks_on_delete_button() {
        homePage.deleteSubmit();
    }

    @Then("User successfully delete the planet with associated moons")
    public void user_successfully_delete_the_planet_with_associated_moons() {
        homePage.closePage();
    }

    @Then("User can not delete the planet")
    public void user_can_not_delete_the_planet() {
        homePage.closePage();
    }




}
