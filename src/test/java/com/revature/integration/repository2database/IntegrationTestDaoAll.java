package com.revature.integration.repository2database;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        IntegrationTestDaoMoon.class,
        IntegrationTestDaoPlanet.class,
        IntegrationTestDaoUser.class
})
public class IntegrationTestDaoAll {
}
