package com.autoc0de;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

        },
        features = {"src/test/resources/features"},
        glue = {"com.autoc0de.steps", "com.core.hooks"},
        tags = "@ExampleTag"
)

public class TestRunnerGrid extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}




