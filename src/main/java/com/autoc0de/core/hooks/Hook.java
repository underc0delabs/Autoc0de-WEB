package com.autoc0de.core.hooks;

import com.autoc0de.core.utility.PropertiesReader;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        String browserMode = reader.getProperty("browserMode");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (browserMode.equals("headless")){
            options.addArguments("headless");
            options.addArguments("disable-gpu");
            options.addArguments("no-sandbox");
            options.addArguments("--lang=es");
        }
        System.out.println("Opening browser...");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
        driver.get(url);
    }

    @After
    public void tearDown(Scenario s){
        if (s.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            s.attach(screenshot, "image/png", "screenshot");
        }
        System.out.println("Closing browser...");
        getDriver().close();
        getDriver().quit();
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

}
