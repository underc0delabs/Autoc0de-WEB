package com.core.hooks;

import com.core.driver.DriverManager;
import com.core.utility.MasterPage;
import com.core.utility.PropertyFileReader;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;

import org.openqa.selenium.*;


import java.io.IOException;
import java.net.MalformedURLException;
;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;


public class Hook {

    private static WebDriver driver;

    public Hook(){
    }

    @Before
     public void before(Scenario scenario) throws IOException{
        String url = PropertyFileReader.getProperty("url");
        LoggerFactory.getLogger(this.getClass()).info("------ Starting Frontend-----" + scenario.getName() + "-----");

        try {
            LoggerFactory.getLogger(this.getClass()).info("------ Driver instance from Hooks-----");
            DriverManager.getInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @After
    public void afterScenarioUI(Scenario scenario) throws IllegalAccessException, NoSuchFieldException, MalformedURLException, InterruptedException {
        LoggerFactory.getLogger(this.getClass()).info("------ Ending -----" + scenario.getName() + "-----");
        if(scenario.isFailed() && DriverManager.isDriverCreated()){
            byte[] screenshot = ((TakesScreenshot)DriverManager.getInstance()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        DriverManager.quitDriver();
        MasterPage.removeWaits();

        //This wait improve the performance when you run parallel tests in selenium grid or selenoid
        //Esta espera mejora la performance cuando ejecutas los test en paralelo en seneium grid o selenoid

        /*LoggerFactory.getLogger(this.getClass()).info("------ Se agrega espera de 5 seg para prueba -----");
        sleep(5000);*/
    }
}
