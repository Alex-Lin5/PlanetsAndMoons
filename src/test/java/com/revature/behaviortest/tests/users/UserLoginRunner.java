package com.revature.behaviortest.tests.users;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/users/UserLogin.feature",
        glue = "com/revature/behaviortest/tests/users"
)
public class UserLoginRunner {
}
