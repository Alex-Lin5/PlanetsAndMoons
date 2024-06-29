package com.revature.end2end.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        "src/test/resources/features/users/UserRegistration.feature",
        "src/test/resources/features/users/UserLogin.feature",
        "src/test/resources/features/planets/PlanetAdd.feature",
        "src/test/resources/features/planets/PlanetDelete.feature",
        "src/test/resources/features/moons/Moon.feature"
    },
    glue = "com/revature/end2end/steps",
    plugin = {
        "pretty", "html:target/pretty/reports/test-reports.html"
    }
)
public class ReportGenerate {
}
