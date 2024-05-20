package com.revature.end2end.tests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/users/UserLogin.feature",
        glue = "com/revature/end2end/tests/steps"
)
public class UserLoginRunner {
}
