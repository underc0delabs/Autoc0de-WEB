package com.autoc0de.hooks;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.PropertiesReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Hook {

    private static WebDriver driver;

    public Hook(){
    }

    @Before
    public void setUp() throws IOException {
        PropertiesReader reader = new PropertiesReader("config.properties");
        String url = reader.getProperty("url");
        System.out.println("Opening browser...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
        driver.get(url);
    }

    @After
    public void tearDown(Scenario s){
        if (s.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            s.attach(screenshot, "image/png", "screenshot");
        }
        System.out.println("Closing browser...");
        getDriver().quit();
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

}
