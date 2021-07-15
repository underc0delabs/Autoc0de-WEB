package com.core.utility;

import com.core.hooks.Hook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.time.Duration;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MasterPage extends Hook {


    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    protected WebDriver driver;

    public MasterPage() {
        this.driver = Hook.getDriver();
        this.wait = new WebDriverWait(getDriver(), 30);
        this.fluentWait = new FluentWait<WebDriver>(this.driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10)).ignoring(Exception.class);
    }


    public WebDriverWait auto_getWait() {
        return this.wait = new WebDriverWait(driver, 30);
    }

    public void auto_getWaitInMiliseconds(long miliSeconds){
        sleep(miliSeconds);
    }

    public Wait<WebDriver> auto_getFluentWait() {
        return this.fluentWait;
    }

    public WebElement auto_getWebElement(By locator) {
        return (WebElement) this.auto_getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> auto_getWebElements(By locator) {
        return (List) this.auto_getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void auto_setClickElement(By locator) {
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setClickElement(element);
    }

    public void auto_setClickElement(WebElement element) {
        element.click();
    }

    public void auto_setClickElementJS(By locator) {
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementJS(element);
    }

    public void auto_setClickElementJS(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", element);
        jse.executeScript("arguments[0].click()", element);
    }

    public void auto_setClickElementActions(By locator) {
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementActions(element);
    }

    public void auto_setClickElementActions(WebElement element) {
        Actions action = new Actions(getDriver());
        action.click(element).perform();
    }

    public void auto_setClickElementAndHold(By locator) {
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementActions(element);
    }

    public void auto_setClickElementAndHold(WebElement element) {
        Actions action = new Actions(getDriver());
        action.clickAndHold(element).perform();
    }

    public void auto_setClickElementAndCheck(By locator){
        WebElement element = auto_getWebElement(locator);
        auto_setClickElementAndCheck(element);
    }

    public void auto_setClickElementAndCheck(WebElement element){
        auto_getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void auto_setTextToInput(By locator, String value) {
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInput(element, value);
    }

    public void auto_setTextToInput(WebElement element, String value) {
        this.auto_setClickElement(element);
        element.clear();
        element.sendKeys(value);
    }

    public void auto_setTextToInputWithoutClear(By locator, String value) {
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setClickElement(element);
        this.auto_setTextToInputWithoutClick(element, value);
    }

    public void auto_setTextToInputWithoutClick(By locator, String value) {
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInputWithoutClick(element, value);
    }

    public void auto_clearInput(By locator){
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

    public void auto_setTextToInputRaw(By locator, String text){
        WebElement element = auto_getWebElement(locator);
        auto_setTextToInputRaw(element, text);
    }

    public void auto_setTextToInputRaw(WebElement element, String text){
        element.sendKeys(text);
    }

    public String auto_getElementText(By locator) {
        WebElement element = this.auto_getWebElement(locator);
        return this.auto_getElementText(element);
    }

    public String auto_getElementText(WebElement element) {
        return element.getText();
    }

    public String auto_getInputValue(By locator) {
        WebElement element = this.auto_getWebElement(locator);
        return this.auto_getInputValue(element);
    }

    public String auto_getInputValue(WebElement element) {
        return element.getAttribute("value");
    }

    public void auto_selectCheckbox(By locator) {
        WebElement checkbox = this.driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public void auto_deselectCheckbox(By locator) {
        WebElement checkbox = this.driver.findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public boolean auto_isElementPresent(By locator) {
        this.driver.manage().timeouts().implicitlyWait(0L, SECONDS);

        boolean var3;
        try {
            this.getDriver().findElement(locator);
            boolean var2 = true;
            return var2;
        } catch (NoSuchElementException var7) {
            var3 = false;
        } finally {
            this.driver.manage().timeouts().implicitlyWait(2L, SECONDS);
        }

        return var3;
    }

    public boolean auto_isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    public boolean auto_isElementVisible(By locator) {
        return this.auto_isElementVisible(this.auto_getWebElement(locator));
    }

    public boolean auto_waitAndCheckElementPresent(By locator) {
        try {
            this.auto_getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException var3) {
            return false;
        }
    }

    public boolean auto_isElementPresent(WebElement element, By locator) {
        this.driver.manage().timeouts().implicitlyWait(0L, SECONDS);

        boolean var4;
        try {
            element.findElement(locator);
            boolean var3 = true;
            return var3;
        } catch (NoSuchElementException var8) {
            var4 = false;
        } finally {
            this.driver.manage().timeouts().implicitlyWait(2L, SECONDS);
        }

        return var4;
    }

    public boolean auto_isInputElementEmpty(By inputLocator) {
        return this.auto_isInputElementEmpty(this.auto_getWebElement(inputLocator));
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

    public void auto_waitForElementDisappears(By locator) {
        this.auto_getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void auto_waitForElementVisibility(By locator) {
        this.auto_getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void auto_waitForElementVisibility(WebElement element) {
        this.auto_getFluentWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void auto_waitForElementInvisibility(By locator) {
        this.auto_getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void auto_waitForElementPresence(By locator) {
        this.auto_getFluentWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void auto_setTextToClipboard(String value) {
        StringSelection stringSelection = new StringSelection(value);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, (ClipboardOwner)null);
    }

    public void auto_waitUntilElementDissappear(By locator) {
        if (this.auto_isElementPresent(locator)) {
            this.auto_getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }

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

    public void auto_setTextToInput(By locator, String value, String placeholder) {
        WebElement element = this.auto_getWebElement(locator);
        this.auto_setTextToInput(element, value, placeholder);
    }

    public String auto_getAttribute(By ElementLocator, String attribute){
        auto_waitForElementPresence(ElementLocator);
        WebElement element = auto_getWebElement(ElementLocator);
        return element.getAttribute(attribute);
    }

    public void auto_waitForElementsVisibilities(By locator){
        auto_getFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void auto_waitForElementsVisibilities(List<WebElement> elements){
        auto_getFluentWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void auto_waitElementNotStale(By locator){
        WebElement element = auto_getWebElement(locator);
        auto_waitElementNotStale(element);
    }

    public void auto_waitElementNotStale(WebElement element){
        auto_getFluentWait().until(ExpectedConditions.stalenessOf(element));
    }

    public void auto_HoverElement(By locator){
        WebElement element = auto_getWebElement(locator);
        auto_HoverElement(element);
    }

    public void auto_HoverElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public WebElement auto_waitElementClickable(By locator){
        WebElement element = auto_getWebElement(locator);
        return auto_waitElementClickable(element);
    }

    public WebElement auto_waitElementClickable(WebElement element){
        return auto_getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void auto_scrollToElement(By locator){
        WebElement element = auto_getWebElement(locator);
        auto_scrollToElement(element);
    }

    public void auto_scrollToElement(WebElement element){
        Actions action = new Actions(getDriver());
        action.moveToElement(element).perform();

    }

    public void auto_sendKeyPressedToElement(By locator, Keys key){
        WebElement element = auto_getWebElement(locator);
        auto_sendKeyPressedToElement(element,key);
    }

    public void auto_sendKeyPressedToElement(WebElement element, Keys key){
        element.sendKeys(key);
    }

    public void auto_waitForLoadPageComplete(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)getDriver();
        auto_getFluentWait().until(driver ->javascriptExecutor.executeScript("return document.readyState").equals("complete"));
    }
}
