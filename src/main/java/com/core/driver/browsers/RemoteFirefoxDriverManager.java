package com.core.driver.browsers;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;


public class RemoteFirefoxDriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteFirefoxDriverManager.class);

    public static RemoteWebDriver createDriver(String gridURL, String browserMode) throws MalformedURLException {
        LOGGER.info("Initializing Remote Chrome Driver ");
        return new RemoteWebDriver(new URL(gridURL), getOptions(browserMode));

    }

    public static FirefoxOptions getOptions(String browserMode) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (browserMode.equals("headless")) {
            firefoxOptions.setHeadless(true);
        }
        firefoxOptions.setCapability("disable-restore-session-state", true);
        firefoxOptions.setCapability("resolution", "1920x1080");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        return firefoxOptions;
    }


}
