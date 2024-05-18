package com.revature.behavior.tests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/users/UserLogin.feature",
        glue = "com/revature/behavior/tests/steps"
)
public class UserLoginRunner {
}
