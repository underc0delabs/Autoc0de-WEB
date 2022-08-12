package com.core.utility;

import com.core.LocatorBuilder;
import com.core.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class MasterPage {


    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal();
    private static ThreadLocal<FluentWait<EventFiringWebDriver>> fluentWait = new ThreadLocal();

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterPage.class);

    private static long STANDARD_TIMEOUT= Long.valueOf(PropertyFileReader.getProperty("standard.wait.timeout"));
    private static long EXPLICIT_TIMEOUT= Long.valueOf(PropertyFileReader.getProperty("explicit.wait.timeout"));
    private static long WAIT_FRECUENCY= Long.valueOf(PropertyFileReader.getProperty("wait.frequency.ms"));


    /**
     * Returns the default wait
     * @return web driver wait
     */
    public WebDriverWait auto_getWait() {
        if (wait.get() == null) {
            wait.set(new WebDriverWait(getDriver(), STANDARD_TIMEOUT));
        }
        return (WebDriverWait)wait.get();
    }

    public Wait<EventFiringWebDriver> auto_getFluentWait() {
        if (fluentWait.get() == null) {
            fluentWait.set((new FluentWait(getDriver())).withTimeout(Duration.ofSeconds(EXPLICIT_TIMEOUT)).pollingEvery(Duration.ofMillis(WAIT_FRECUENCY)).ignoring(NoSuchElementException.class));
        }

        return (Wait)fluentWait.get();
    }

    public void auto_getWaitInMiliseconds(long miliSeconds){
        sleep(miliSeconds);
    }

    /** Clen waits**/
    public static void removeWaits() {
        wait.remove();
        fluentWait.remove();
    }


    public RemoteWebDriver getDriver() {
        return DriverManager.getInstance();
    }

      public void auto_maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public void auto_openURLInBrowser(String url) {
        getDriver().get(url);
        auto_waitForPageToLoad();
    }

    public void auto_waitForPageToLoad() {
        JavascriptExecutor jse = getDriver();
        this.auto_getWait().until(driver -> jse.executeScript("return document.readyState").equals("complete"));
    }

    public String auto_getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }


    public String auto_getPageTitle() {
        return getDriver().getTitle();
    }


    public boolean auto_checkPageTitleContains(String title) {
        return  auto_getFluentWait().until(ExpectedConditions.titleContains(title));
    }


    public boolean auto_checkPageUrlContains(String fraction) {
        return auto_getFluentWait().until(ExpectedConditions.urlContains(fraction));
    }

    private WebElement auto_getWebElement(By locator) {
         WebElement element=null;
            try {
                element = getDriver().findElement(locator);
            } catch (Exception e1) {
                try {
                    element =this.auto_getWait().until(ExpectedConditions.presenceOfElementLocated(locator));

                } catch (Exception e2) {
                    LOGGER.error("Element '"+ locator+"' not present - Exception: ".concat(e2.getMessage()));
                    throw(e2);

                }
            }
            return element;
        //}
       //return (WebElement) this.auto_getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement auto_getWebElement(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        return this.auto_getWebElement(locator);
    }

    public List<WebElement> auto_getWebElements(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        return (List) this.auto_getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void auto_setClickElement(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = null;
        try {
            element = this.auto_getWebElement(locator);
            this.auto_setClickElement(element);

        } catch (Exception e1) {
            try {
                element = this.auto_getWebElement(locator);
                this.auto_waitForElementVisibility(element);
                this.auto_waitElementClickable(element);
                this.auto_setClickElement(element);

            } catch (ElementNotInteractableException e) {
                this.auto_setClickElementJS(element);
                LOGGER.info("The element was clicked with JS: "+locator);

            } catch (StaleElementReferenceException e) {
                this.retryingFindClick(elementPath, elementValue);
            }
        }
    }

    private boolean retryingFindClick(String elementPath, String ... elementValue) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 3) {
            try {
                By locator =  LocatorBuilder.getBy(elementPath, elementValue);
                getDriver().findElement(locator).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
                auto_getWaitInMiliseconds(500);
            }
            attempts++;
        }
        return result;
    }

    private void auto_setClickElement(WebElement element) {
        element.click();
    }

    public void auto_setClickElementJS(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementJS(element);
    }

    private void auto_setClickElementJS(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", element);
        jse.executeScript("arguments[0].click()", element);
    }

    public void auto_setClickElementActions(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementActions(element);
    }

    public void auto_setClickElementActions(WebElement element) {
        Actions action = new Actions(getDriver());
        action.click(element).perform();
    }

    public void auto_setClickElementAndHold(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementActions(element);
    }

    public void auto_setClickElementAndHold(WebElement element) {
        Actions action = new Actions(getDriver());
        action.clickAndHold(element).perform();
    }

    public void auto_setClickElementAndCheck(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementAndCheck(element);
    }

    public void auto_setClickElementAndCheck(WebElement element){
        auto_getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void auto_setTextToInput(String elementPath, String value, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        auto_setClickElement(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInput(element, value);
    }

    public void auto_setTextToInput(WebElement element, String value) {
        //this.auto_setClickElement(element);
        element.clear();
        element.sendKeys(value);
    }

    public void auto_setTextToInputActions(String elementPath, String value, String ... elementValue){
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInputActions(element, value);
    }

    public void auto_setTextToInputActions(WebElement element, String value){
        Actions actions = new Actions(getDriver());
        actions.sendKeys(element,value).perform();
    }

    public void auto_setTextToInputWithoutClear(String elementPath, String value, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setClickElement(element);
        this.auto_setTextToInputWithoutClear(element, value);
    }

    public void auto_setTextToInputWithoutClick(String elementPath, String value, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInputWithoutClick(element, value);
    }

    public void auto_clearInput(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        this.auto_clearInput(element);
    }

    public void auto_clearInput(WebElement element){
        element.clear();
    }

    public void auto_setTextToInputWithoutClear(WebElement element, String value) {
        this.auto_setClickElement(element);
        element.sendKeys(new CharSequence[]{value});
    }

    public void auto_setTextToInputWithoutClick(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void auto_setTextToInputRaw (String elementPath, String text, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = auto_getWebElement(locator);
        auto_setTextToInputRaw(element, text);
    }

    public void auto_setTextToInputRaw(WebElement element, String text){
        element.sendKeys(text);
    }

    public String auto_getElementText(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        return this.auto_getElementText(element);
    }

    public String auto_getElementText(WebElement element) {
        return element.getText();
    }

    public String auto_getInputValue(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement element = this.auto_getWebElement(locator);
        return this.auto_getInputValue(element);
    }

    public String auto_getInputValue(WebElement element) {
        return element.getAttribute("value");
    }

    public void auto_selectCheckbox(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement checkbox = auto_getWebElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public void auto_deselectCheckbox(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        WebElement checkbox = getDriver().findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public boolean auto_isElementPresent(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);

        boolean var;
        try {
            //this.auto_getWebElement(locator);
            this.getDriver().findElement(locator); //TODO Revisar!
            var=true;
        }catch (Exception e) {
            var=false;
        }
        return var;
    }

    @Deprecated
    public boolean auto_isElementPresent(WebElement element, By locator) {
        getDriver().manage().timeouts().implicitlyWait(0L, SECONDS);
        boolean var4;
        try {
            element.findElement(locator);
            boolean var3 = true;
            return var3;
        } catch (NoSuchElementException var8) {
            var4 = false;
        } finally {
            getDriver().manage().timeouts().implicitlyWait(2L, SECONDS);
        }
        return var4;
    }

    //Revisar
    public boolean auto_isElementVisible(WebElement element) {
        // Todo revisar espera
        this.auto_getFluentWait().until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public boolean auto_isElementVisible(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        boolean var;
        try {
            var=this.auto_isElementVisible(this.auto_getWebElement(locator));
        }catch (Exception e) {
            LOGGER.info("The element is not visible, return FALSE");
            var=false;
        }
        return var;
    }

    public boolean auto_isElementVisibleWithoutWait(WebElement element) {
        return element.isDisplayed();
    }

    public boolean auto_isElementVisibleWithoutWait(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        boolean var;
        try {
            this.auto_isElementVisibleWithoutWait(getDriver().findElement(locator));
            var=true;
        } catch (Exception e) {
            var=false;
        }
        return var;
    }

    public boolean auto_isInputElementEmpty(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        return this.auto_isInputElementEmpty(this.auto_getWebElement(locator));
    }

    public boolean auto_isInputElementEmpty(WebElement element) {
        return element.getAttribute("value").isEmpty();
    }

    public boolean auto_isElementEmpty(By locator) {
        return this.auto_isElementEmpty(this.auto_getWebElement(locator));
    }

    public boolean auto_isElementEmpty(WebElement element) {
        return element.getText().isEmpty();
    }

    public void auto_waitForElementDisappears(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        if (auto_isElementVisibleWithoutWait(elementPath, elementValue)){
            try {
                this.auto_getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }catch (Exception e) {
                LOGGER.info("The element is not longer visible");
            }
        }
        LOGGER.info("The element wasn't visible");
    }

    public WebElement auto_waitForElementVisibility(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        return this.auto_getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public WebElement auto_waitForElementVisibility(WebElement element) {
        return this.auto_getFluentWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void auto_waitForElementInvisibility(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        this.auto_getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Deprecated // se encuentra implicito en auto_getWebElement
    public void auto_waitForElementPresence(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        this.auto_getFluentWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void auto_waitForElementToHaveText(String elementPath, String text, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        this.auto_getFluentWait().until(ExpectedConditions.textToBe(locator,text));
    }

    public void auto_setTextToClipboard(String value) {
        StringSelection stringSelection = new StringSelection(value);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, (ClipboardOwner)null);
    }

    public boolean auto_isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var4) {
            System.out.println("Error in sleep: ".concat(var4.getMessage()));
            var4.printStackTrace();
        }

    }

    public void auto_setTextToInput(WebElement element, String value, String placeholder) {
        if (!Utils.isTextFieldEmpty(element, placeholder)) {
            element.clear();
        }

        this.auto_setTextToInputWithoutClear(element, value);
    }

    public void auto_setTextToInput(String elementPath, String value, String placeholder, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInput(this.auto_getWebElement(locator), value, placeholder);
    }

    public String auto_getAttribute(String elementPath, String attribute, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //auto_waitForElementPresence(locator);
        WebElement element = auto_getWebElement(locator);
        return element.getAttribute(attribute);
    }

    public void auto_waitForElementsVisibilities(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        auto_getFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void auto_waitForElementsVisibilities(List<WebElement> elements){
        auto_getFluentWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void auto_HoverElement(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //WebElement element = auto_getWebElement(locator);
        auto_HoverElement(this.auto_getWebElement(locator));
    }

    public void auto_HoverElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public WebElement auto_waitElementClickable(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //WebElement element = auto_getWebElement(locator);
        return auto_waitElementClickable(this.auto_getWebElement(locator));
    }

    private WebElement auto_waitElementClickable(WebElement element){
        return auto_getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void auto_scrollToElement(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //WebElement element = auto_getWebElement(locator);
        auto_scrollToElement(this.auto_getWebElement(locator));
    }

    public void auto_scrollToElement(WebElement element){
        Actions action = new Actions(getDriver());
        action.moveToElement(element).perform();

    }

    public void auto_sendKeyPressedToElement(String elementPath, Keys key, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        auto_sendKeyPressedToElement(this.auto_getWebElement(locator),key);
    }

    public void auto_sendKeyPressedToElement(WebElement element, Keys key){
        element.sendKeys(key);
    }


    public void scrollToElementJS(int x, int y) {
        JavascriptExecutor javScriptExecutor = (JavascriptExecutor)getDriver();
        javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");
    }

    public void scrollUpJS() {
        JavascriptExecutor Scrool = (JavascriptExecutor)getDriver();
        Scrool.executeScript("window.scrollBy(0,-300)", "");
    }

    public void scrollDownJS() {
        JavascriptExecutor Scrool = (JavascriptExecutor)getDriver();
        Scrool.executeScript("window.scrollBy(0,300)", "");
    }

    public void auto_makeElementVisible(String elementPath, String ... elementValue) {
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        //WebElement element = auto_getWebElement(locator);
        auto_makeElementVisible(this.auto_getWebElement(locator));
    }

    public void auto_makeElementVisible(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("arguments[0].style.height='auto'; arguments[0].style.visibility='visible'", element);
    }

    public void auto_dragAndDrop(String elementFrom, String elementTo, String ... elementValue){
        By locatorFrom = LocatorBuilder.getBy(elementFrom, elementValue);
        By locatorTo = LocatorBuilder.getBy(elementTo, elementValue);
        auto_dragAndDrop(this.auto_getWebElement(locatorFrom), this.auto_getWebElement(locatorTo));
    }

    public void auto_dragAndDrop(WebElement elementFrom, WebElement elementTo){
        Actions act = new Actions(getDriver());
        act.dragAndDrop(elementFrom, elementTo).build().perform();
    }

    public void auto_switchToIframe(String elementPath, String ... elementValue){
        By locator =  LocatorBuilder.getBy(elementPath, elementValue);
        this.auto_getFluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(auto_getWebElement(locator)));
        //getDriver().switchTo().frame(getDriver().findElement(locator));
    }

    public void auto_switchToDefaultIframe(){
        getDriver().switchTo().defaultContent();
    }

}
