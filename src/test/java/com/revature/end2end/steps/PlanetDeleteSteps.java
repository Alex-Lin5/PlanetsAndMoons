package com.revature.end2end.steps;

import com.revature.end2end.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class PlanetDeleteSteps {
    HomePage homePage;
    private static String planetPassId;

    @Given("User found a valid Planet in home page")
    public void user_found_a_valid_planet_in_home_page() {
        planetPassId = "0";
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
