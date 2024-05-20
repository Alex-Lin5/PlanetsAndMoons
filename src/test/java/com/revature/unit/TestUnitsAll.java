package com.revature.unit;

import com.revature.unit.service.TestServicesAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestServicesAll.class
})
public class TestUnitsAll {
}
