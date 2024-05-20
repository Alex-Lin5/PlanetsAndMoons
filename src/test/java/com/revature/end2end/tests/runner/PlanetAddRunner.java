package com.revature.end2end.tests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/planets/PlanetAdd.feature",
        glue = "com/revature/end2end/tests/steps"
)
public class PlanetAddRunner {
}
