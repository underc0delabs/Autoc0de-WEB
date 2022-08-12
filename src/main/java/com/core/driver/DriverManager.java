package com.core.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);

    public static RemoteWebDriver getInstance() {
        if (!isDriverCreated()) {
            if (driver.get() != null) {
                driver.remove();
            }

            driver.set(DriverFactory.init_driver());
        }
        return driver.get();
    }

    public static boolean isDriverCreated() {
        return driver.get() != null;
    }

    public static void quitDriver() {
        if (isDriverCreated()) {
            try {
                driver.get().close();
                driver.get().quit();
                driver.remove();

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

        }
    }
}
