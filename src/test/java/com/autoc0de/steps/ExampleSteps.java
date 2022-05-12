package com.autoc0de.steps;

import com.autoc0de.pages.ExamplePage;
import io.cucumber.java.en.*;

public class ExampleSteps {

    /*
    ** PAGE INSTANCE
     */

    ExamplePage examplePage = new ExamplePage();

    @Given("the user is on the home screen of Underc0de.org")
    public void theUserIsOnTheHomeScreenOfUndercDeOrg() {
        examplePage.verifyHomeTitle();
    }

    @And("^the user click the (.*) button$")
    public void theUserClickTheButton(String button) {
        examplePage.clickButtonSwitch(button);
    }

    @And("^the user complete te user information. Usuario: (.*) Contraseña: (.*)$")
    public void theUserCompleteTeUserInformationUsuarioUserContraseñaPass(String user, String pass) {
        examplePage.completeLoginData(user, pass);

    }

    @Then("the user verifies that they are logged in.")
    public void theUserVerifiesThatTheyAreLoggedIn() {
        examplePage.verifyLogin();
    }
}
