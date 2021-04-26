package com.autoc0de.core.hooks;

import com.autoc0de.core.utility.PropertiesReader;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Hook {

    private static WebDriver driver;

    public Hook(){
    }

    @Before
    public void setUp() throws IOException {
        PropertiesReader reader = new PropertiesReader("config.properties");
        String browserName = reader.getProperty("browserName");
        browser(browserName);
    }

    public void browser(String browserName) throws IOException{
        PropertiesReader reader = new PropertiesReader("config.properties");
        String browserMode = reader.getProperty("browserMode");
        String url = reader.getProperty("url");
        Dimension dimension = new Dimension(1920, 1080);
        switch (browserName){
            case "iExplorer":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions optionsIE = new InternetExplorerOptions();
                optionsIE.disableNativeEvents();
                optionsIE.enablePersistentHovering();
                optionsIE.ignoreZoomSettings();
                optionsIE.setCapability("ignoreProtectedModeSettings", true);
                optionsIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                System.out.println("Opening browser...");
                driver = new InternetExplorerDriver(optionsIE);
                driver.manage().window().setSize(dimension);
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(200,TimeUnit.SECONDS);
                driver.get(url);
                break;
            case "mozilla":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsF = new FirefoxOptions();
                if (browserMode.equals("headless")){
                    optionsF.setHeadless(true);
                }
                System.out.println("Opening browser...");
                driver = new FirefoxDriver(optionsF);
                driver.manage().window().setSize(dimension);
                driver.manage().timeouts().pageLoadTimeout(200,TimeUnit.SECONDS);
                driver.get(url);
                break;
            case "safari":
                System.out.println("Trabajando en ello");
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsGC = new ChromeOptions();
                if (browserMode.equals("headless")){
                    optionsGC.addArguments("headless");
                    optionsGC.addArguments("disable-gpu");
                    optionsGC.addArguments("no-sandbox");
                    optionsGC.addArguments("--lang=es");
                }
                System.out.println("Opening browser...");
                driver = new ChromeDriver(optionsGC);
                driver.manage().window().setSize(dimension);
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(200,TimeUnit.SECONDS);
                driver.get(url);
                break;
            default:
                System.out.println("Browser: "+browserName+" invalid... Please check configuration");
                break;
        }
    }

    @After
    public void tearDown(Scenario s)throws IOException{
        PropertiesReader reader = new PropertiesReader("config.properties");
        String browserName = reader.getProperty("browserName");
        if (s.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            s.attach(screenshot, "image/png", "screenshot");
        }
        System.out.println("Closing browser...");
        if (browserName.contains("mozilla")){
            getDriver().close();
        } else {
            getDriver().close();
            getDriver().quit();
        }
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

}
