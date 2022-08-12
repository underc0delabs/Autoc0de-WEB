package com.core.driver.browsers;

import com.core.driver.DriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager extends DriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChromeDriverManager.class);


    public static RemoteWebDriver createDriver(String browserMode) {
        LOGGER.info("Initializing Chrome Driver");
        WebDriverManager.getInstance(CHROME).setup();

        ChromeOptions options = new ChromeOptions();
        if (browserMode.equals("headless")) {
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            options.addArguments("--disable-dev-shm-usage");
        }

        //options.addArguments("window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        return new ChromeDriver(options);
    }
}