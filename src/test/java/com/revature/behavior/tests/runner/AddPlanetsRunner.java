package com.revature.behavior.tests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/planets/addPlanets.feature",
        glue = "com/revature/behavior/tests/steps"
)
public class AddPlanetsRunner {
}
