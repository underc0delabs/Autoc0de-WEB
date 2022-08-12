package com.core.driver.browsers;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class RemoteChromeDriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteChromeDriverManager.class);

    public static RemoteWebDriver createDriver(String gridURL, String browserMode) throws MalformedURLException {
        LOGGER.info("Initializing Remote Chrome Driver ");
        return new RemoteWebDriver(new URL(gridURL), getOptions(browserMode));

    }

    public static ChromeOptions getOptions(String browserMode) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (browserMode.equals("headless")) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-dev-shm-usage");
        }
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("window-size=1920,1080");
        Map<String, Boolean> selenoid=new HashMap();
        selenoid.put("enableVNC", true);
        selenoid.put("enableVideo", false);
        chromeOptions.setCapability("selenoid:options", selenoid);

        return chromeOptions;
    }


}
