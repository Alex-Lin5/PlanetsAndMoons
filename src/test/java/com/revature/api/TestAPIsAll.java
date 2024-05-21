package com.revature.api;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestSystemPlanet.class,
        TestSystemMoon.class,
        TestSystemUser.class
})
public class TestAPIsAll {
}
