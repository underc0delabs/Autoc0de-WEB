package com.autoc0de.steps;

import com.autoc0de.pages.ExamplePage;
import io.cucumber.java.en.*;

public class ExampleSteps {

    //PAGE INSTANCE
    ExamplePage examplePage = new ExamplePage();

    @Given("the user is on the home screen of Underc0de.org")
    public void theUserIsOnTheHomeScreenOfUndercDeOrg() {
        examplePage.verifyHomeTitle();
    }

    @When("^the user hovers the mouse over the tab (.*)$")
    public void theUserHoversTheMouseOverTheTab(String tab) {
        examplePage.clickOnINDEXOFTab();
    }

    @And("^the user click the (.*) button")
    public void theUserClickTheButton(String button) {
        examplePage.clickOnDE0AHACKINGButton();
        examplePage.verifyDE0AHACKINGTitle();
    }

    @And("the user click all the rigth arrows")
    public void theUserClickAllTheRigthArrows() {
        examplePage.clickAllArrows();
    }

    @Then("the user verifies all the text in the cards")
    public void theUserVerifiesAllTheTextInTheCards() {
        examplePage.verifyAllCardsInformation();
    }
}
