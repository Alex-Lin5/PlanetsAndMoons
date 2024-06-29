package com.revature.end2end.runner;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/planets/PlanetAdd.feature",
        glue = "com/revature/end2end/steps"
)
public class PlanetAddRunner {
}
