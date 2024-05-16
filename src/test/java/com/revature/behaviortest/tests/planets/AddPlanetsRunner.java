package com.revature.behaviortest.tests.planets;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/planets/addPlanets.feature",
        glue = "com/revature/behaviortest/tests/planets"
)
public class AddPlanetsRunner {
}
