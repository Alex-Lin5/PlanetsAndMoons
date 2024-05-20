package com.revature.integration.repository2database;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        IntegrationTestMoon.class,
        IntegrationTestPlanet.class,
        IntegrationTestUser.class
})
public class IntegrationTestAll {
}
