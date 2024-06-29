package com.revature.end2end.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
            "src/test/resources/features/planets/PlanetAdd.feature",
            "src/test/resources/features/planets/PlanetDelete.feature",
            "src/test/resources/features/planets/PlanetGet.feature",

        },
        glue = "com/revature/end2end/steps"
)
public class PlanetRunner {
}
