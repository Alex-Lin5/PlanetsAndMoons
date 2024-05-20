package com.revature.unit.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestMoonService.class,
        TestPlanetService.class,
        TestUserService.class
})
public class TestServicesAll {
}
