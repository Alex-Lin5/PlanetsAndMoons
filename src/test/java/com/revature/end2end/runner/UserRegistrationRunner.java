package com.revature.end2end.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/users/UserRegistration.feature",
        glue = "com/revature/end2end/steps"
//        plugin = {
//                "pretty",
//                "html:src/test/resources/reports/html-reports.html"
//        }
)
public class UserRegistrationRunner {

}
