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


    public ExamplePage() {
    }

    //FUNCTIONS

    public void verifyHomeTitle(){
        Assert.assertTrue(auto_isElementVisible(By.xpath(HOME_TITLE_XPATH)));
    }

    public void clickOnINDEXOFTab(){
        auto_setClickElement(By.xpath(INDEXOF_TAB_XPATH));
    }

    public void clickOnDE0AHACKINGButton(){
        auto_setClickElement(By.xpath(DE_0_A_HACKING_BUTTON_XPATH));
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
}
