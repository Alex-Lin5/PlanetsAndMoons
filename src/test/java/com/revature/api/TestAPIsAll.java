package com.revature.api;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestAPIsPlanet.class,
        TestAPIsMoon.class,
        TestAPIsUser.class
})
public class TestAPIsAll {
}
