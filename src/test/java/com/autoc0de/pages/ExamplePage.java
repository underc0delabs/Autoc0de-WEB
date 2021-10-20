package com.autoc0de.pages;


import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.autoc0de.core.utility.MasterPage;

import java.util.List;

public class ExamplePage extends MasterPage {

    SoftAssert softAssert = new SoftAssert();

    /*
     ** CONSTANTS **
     */

    private final String HOME_TITLE_XPATH = "//*[@id=\"top\"]/section[1]/div/div/div/div/h2";
    private final String COMUNIDAD_TAB_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[4]/a";
    private final String BENEFICIOS_BUTTON_XPATH = "//a[.='BENEFICIOS']";
    private final String LIST_CARDS_ARROW_XPATH = "//*[@class=\"fa fa-ellipsis-v right\"]";
    private final String LIST_CARDS_REVEAL_XPATH = "//div[@class='portfolio portfolio-with-title col-3 gutter mtb-50 shuffle']/div[1]//div[@class='card-reveal']";
    private final String FORO_BUTTON_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[2]/a";
    private final String INGRESAR_BUTTONS_XPATH = "//*[@id=\"button_login\"]/a";
    private final String INGRESAR_LOGIN_BUTTONS_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/p[1]/input";
    private final String USUARIO_INPUT_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[1]/input";
    private final String PASS_INPUT_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[2]/input";
    private final String USER_LOGIN_LBL_XPATH = "//*[@id=\"navbarDropdown\"]/span";

    /*
     ** //FUNCTIONS **
     */

    public void verifyHomeTitle(){
        Assert.assertTrue(auto_isElementVisible(By.xpath(HOME_TITLE_XPATH)));
    }

    public void clickOnCOMUNIDADTab(){
        auto_setClickElement(By.xpath(COMUNIDAD_TAB_XPATH));
    }

    public void clickButtonSwitch(String button){
        switch (button.toLowerCase()) {
            case "beneficios":
                clickOnBENEFICIOSButton();
                break;
            case "foro":
                clickOnForoButton();
                break;
            case "ingresar":
                clickOnIngresarButtons();
                break;
            default:
                Assert.assertEquals(button,"No button matched", "Invalid button options");
        }
    }

    public void clickOnBENEFICIOSButton(){
        auto_setClickElement(By.xpath(BENEFICIOS_BUTTON_XPATH));
    }

    public void clickOnForoButton(){
        auto_setClickElement(By.xpath(FORO_BUTTON_XPATH));
    }

    public void clickOnIngresarButtons(){
        if (auto_isElementPresent(By.xpath(INGRESAR_LOGIN_BUTTONS_XPATH))){
            auto_setClickElement(By.xpath(INGRESAR_LOGIN_BUTTONS_XPATH));
        }else{
            auto_setClickElement(By.xpath(INGRESAR_BUTTONS_XPATH));
        }
    }

    public void clickAllArrows(){
        List<WebElement> listArrows = auto_getWebElements(By.xpath(LIST_CARDS_ARROW_XPATH));
        for (WebElement item: listArrows) {
            item.click();
        }
    }

    public void verifyAllCardsInformation(){
        List<WebElement> listCards = auto_getWebElements(By.xpath(LIST_CARDS_REVEAL_XPATH));
        for (WebElement item: listCards) {
            softAssert.assertTrue(auto_isElementVisible(item), "The item isn't visible");
        }
        softAssert.assertAll();
    }

    public void completeLoginData(String user, String pass){
        auto_waitForElementPresence(By.xpath(USUARIO_INPUT_XPATH));
        auto_setTextToInput(By.xpath(USUARIO_INPUT_XPATH), user);
        auto_setTextToInput(By.xpath(PASS_INPUT_XPATH), pass);
    }

    public void verifyLogin(){
        Assert.assertTrue(auto_getElementText(By.xpath(USER_LOGIN_LBL_XPATH)).toLowerCase().contains("autoc0de"), "Error at login - Invalid username or passwor");
    }
}
