package com.autoc0de;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

        },
        features = {"src/test/resources/features"},
        glue = {"com.autoc0de.steps","com.core.hooks"},
        tags =  "@ExampleTag"

)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

}



