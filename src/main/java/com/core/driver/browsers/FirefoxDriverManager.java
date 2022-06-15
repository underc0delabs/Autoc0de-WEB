package com.core.driver.browsers;

import com.core.driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class FirefoxDriverManager extends DriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirefoxDriverManager.class);

    public static RemoteWebDriver createDriver(String browserMode) {
        LOGGER.info("Initializing Firefox Driver");
        WebDriverManager.getInstance(FIREFOX).setup();

        FirefoxOptions options = new FirefoxOptions();
        if (browserMode.equals("headless")) {
            options.setHeadless(true);
        }

        options.setCapability("disable-restore-session-state", true);
        options.setCapability("resolution", "1920x1080");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);


        return new FirefoxDriver(options);
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability("applicationCacheEnabled", false);
        options.setCapability("disable-restore-session-state", true);
        options.setCapability("resolution", "1920x1080");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        return options;
    }

}
