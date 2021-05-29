package definitions.ck12;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ck12.MainPage;
import pages.ck12.SearchPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CK12Steps {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @Given("Navigate to the {string} page")
    public void navigateToThePage(String page){
        switch (page){
            case "Main":
                mainPage.open();
                break;

            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @When("I click on the sign in button in the header")
    public void iClickOnTheSignInButtonInTheHeader() {
        mainPage.clickOnSignInButton();
    }

    @Then("Verify Sign In popup should be visible")
    public void verifySignInPopupShouldBeVisible() {
        assertThat(mainPage.signInIsVisible()).isTrue();
    }

    @When("Click on the Google Translate widget button")
    public void clickOnTheGoogleTranslateWidgetButton() {
        mainPage.clickOnGoogleTranslateWidgetButton();
    }

    @Then("Google Translate iframe should be displayed")
    public void googleTranslateIframeShouldBeDisplayed() {
        assertThat(mainPage.googleTranslateWidgetIframeIsVisible()).isTrue();
    }

    @Then("Switch to the Google Translate iframe")
    public void switchToTheGoogleTranslateIframe() {
        mainPage.switchToGoogleTranslateIframe();
    }

    @Then("Verify {string} text presents")
    public void verifyTextPresents(String text) {
        assertThat(mainPage.getGoogleTranslateSelectLanguageSpanText()).contains(text);
    }

    @Then("Verify Footer presents on a page")
    public void verifyFooterPresentsOnAPage() {
        assertThat(mainPage.verifyFooterPresence()).isTrue();
    }

    @When("I type {string} to the search field in header")
    public void iTypeToTheSearchFieldInHeader(String text) {
        searchPage.typeTextToSearchFieldInHeader(text);
    }

    @And("I click on the search button in header")
    public void iClickOnTheSearchButtonInHeader() {
        searchPage.clickOnTheSearchButton();
    }

    @Then("Verify search query header contains text {string}")
    public void verifySearchQueryHeaderContainsText(String text) {
        assertThat(searchPage.getSearchQueryHeaderText()).contains(text);
    }

    @When("Scroll to the footer with offset {int}")
    public void scrollToTheFooterWithOffset(int offset) {
        searchPage.scrollToTheFooter(offset);
    }

    @Then("Set the language {string} in the Google Translate iframe")
    public void setTheLanguageInTheGoogleTranslateIframe(String language) {
        searchPage.googleTranslateSetLanguage(language);
    }

    @Then("Verify Science span contains text {string}")
    public void verifyScienceSpanContainsText(String text) {
        assertThat(mainPage.getScienceSpanText()).contains(text);
    }
}
