package com.core.driver;

import com.core.driver.browsers.ChromeDriverManager;
import com.core.driver.browsers.FirefoxDriverManager;
import com.core.driver.browsers.RemoteChromeDriverManager;
import com.core.driver.browsers.RemoteFirefoxDriverManager;
import com.core.utility.PropertyFileReader;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class DriverFactory {

    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private static final String browser_config = PropertyFileReader.getProperty("browserName");
    private static final String browsermode_config = PropertyFileReader.getProperty("browserMode");
    private static final String target_config = PropertyFileReader.getProperty("target");


    public static RemoteWebDriver init_driver() {
        String target = target_config;
        switch (target) {
            case"local":
                initDriverLocal();
                //return driver.get();
                break;
            case"grid":
                initDriverGrid();
                //return driver.get();
            break;
            default:
                LOGGER.info("No valid value for Target received: " + target);
        }
        return driver.get();
    }


        public static RemoteWebDriver initDriverLocal() {
            String browser = browser_config;
            String browserMode = browsermode_config;
            LOGGER.info("browser value is: " + browser);
            switch (browser.toLowerCase()) {
                case "mozilla":
                    driver.set(FirefoxDriverManager.createDriver(browserMode));
                    break;
                case "chrome":
                    driver.set(ChromeDriverManager.createDriver(browserMode));
                    break;
                case "edge":
                    LOGGER.info("Edge is not configurated");
                    break;
                case "safari":
                    LOGGER.info("Safari is not configurated");
                    break;
                default:
                    LOGGER.info("No valid value for Browser received: " + browser);
            }
            return driver.get();
        }

        public static RemoteWebDriver initDriverGrid () {
            String browser = browser_config;
            String browserMode = browsermode_config;
            String gridURL = PropertyFileReader.getProperty("grid.url");
            LOGGER.info("browser value is: " + browser + " open in grid " +gridURL);
            switch (browser.toLowerCase()) {
                case "mozilla":
                    try {
                        driver.set(RemoteFirefoxDriverManager.createDriver(gridURL, browserMode));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chrome":
                    try {
                        driver.set(RemoteChromeDriverManager.createDriver(gridURL, browserMode));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "edge":
                    LOGGER.info("Edge is not configurated");
                    break;
                case "safari":
                    LOGGER.info("Safari is not configurated");
                    break;
                default:
                    LOGGER.info("No se recibio un valor esperado para Browser: " + browser);
            }
            return driver.get();
        }
        public static WebDriver getInstanceInit () {
            return driver.get();
        }
    }
