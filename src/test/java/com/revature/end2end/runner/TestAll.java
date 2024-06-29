package com.revature.end2end.runner;

import com.revature.utilities.ConnectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import java.sql.SQLException;

@Suite
@SelectClasses({ReportGenerate.class})
public class TestAll {
    @BeforeAll
    public static void setupAll() throws SQLException {
        ConnectionUtil.deleteTables();
    }
}
