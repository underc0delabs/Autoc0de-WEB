package com.autoc0de.pages;

import com.core.utility.MasterPage;
import org.testng.Assert;

import static com.autoc0de.locators.ExampleLocators.*;

public class ExamplePage extends MasterPage {

    public void navigateToMainURL(){
        auto_openURLInBrowser(BASE_URL);
    }

    public void verifyHomeTitle(){
        Assert.assertTrue(auto_isElementVisible(HOME_TITLE_XPATH));
    }

    public void clickButtonSwitch(String button){
        switch (button.toLowerCase()) {
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

    public void clickOnForoButton(){
        auto_setClickElement(FORO_BUTTON_XPATH);
    }

    public void clickOnIngresarButtons(){
        auto_waitForElementVisibility(INGRESAR_BUTTONS_XPATH);
        auto_setClickElement(INGRESAR_BUTTONS_XPATH);
    }

    public void clickIngresarButtonForum() {
        auto_setClickElement(INGRESAR_LOGIN_BUTTONS_XPATH);
    }

    public void completeLoginData(String user, String pass){
        auto_waitForElementPresence(USUARIO_INPUT_XPATH);
        auto_setTextToInput(USUARIO_INPUT_XPATH, user);
        auto_setTextToInput(PASS_INPUT_XPATH, pass);
    }

    public void verifyLogin(){
        Assert.assertTrue(auto_getElementText(USER_LOGIN_LBL_XPATH).toLowerCase().contains("autoc0de"), "Error at login - Invalid username or passwor");
    }



}
