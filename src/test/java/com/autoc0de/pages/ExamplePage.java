package com.autoc0de.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.MasterPage;

import java.io.IOException;
import java.util.List;

public class ExamplePage extends MasterPage {

    //PAGE INSTANCE
    SoftAssert softAssert = new SoftAssert();

    //CONSTANTS
    private final String HOME_TITLE_XPATH = "//*[@id=\"top\"]/section[1]/div/div/div/div/h2";
    private final String INDEXOF_TAB_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[5]/a";
    private final String DE_0_A_HACKING_BUTTON_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[5]/ul/li/a";
    private final String DE_0_A_HACKING_TITLE_XPATH = "//*[@id=\"hacking\"]/div/div[1]/h2";
    private final String LIST_CARDS_ARROW_XPATH = "//*[contains(@class,'fa-arrow-right')]";
    private final String LIST_CARDS_REVEAL_XPATH = "//*[@class=\"card-reveal\"]";
    private final String FORO_BUTTON_XPATH = "//*[@id=\"materialize-menu\"]/ul/li[2]/a";
    private final String INGRESAR_BUTTONS_XPATH = "//*[@id=\"button_login\"]/a";
    private final String INGRESAR_LOGIN_BUTTONS_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/p[1]/input";
    private final String USUARIO_INPUT_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[1]/input";
    private final String PASS_INPUT_XPATH = "//*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[2]/input";
    private final String USER_LOGIN_LBL_XPATH = "//*[@id=\"navbarDropdown\"]/span";


    public ExamplePage() {
    }

    //FUNCTIONS

    public void verifyHomeTitle(){
        Assert.assertTrue(auto_isElementVisible(By.xpath(HOME_TITLE_XPATH)));
    }

    public void clickOnINDEXOFTab(){
        auto_setClickElement(By.xpath(INDEXOF_TAB_XPATH));
    }

    public void clickButtonSwitch(String button){
        switch (button.toLowerCase()) {
            case "de 0 a hacking":
                clickOnDE0AHACKINGButton();
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

    public void clickOnDE0AHACKINGButton(){
        auto_setClickElement(By.xpath(DE_0_A_HACKING_BUTTON_XPATH));
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

    public void verifyDE0AHACKINGTitle(){
        Assert.assertTrue(auto_isElementVisible(By.xpath(DE_0_A_HACKING_TITLE_XPATH)));
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
        auto_setTextToInput(By.xpath(USUARIO_INPUT_XPATH), user);
        auto_setTextToInput(By.xpath(PASS_INPUT_XPATH), pass);
    }

    public void verifyLogin(){
        Assert.assertTrue(auto_getElementText(By.xpath(USER_LOGIN_LBL_XPATH)).toLowerCase().contains("autoc0de"), "Error at login - Username or passwor error");
    }
}
