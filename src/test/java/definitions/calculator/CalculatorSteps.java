package definitions.calculator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import pages.calculator.MainPage;
import pages.calculator.SalaryCalculatorPage;

import static org.assertj.core.api.Assertions.*;

public class CalculatorSteps {

    MainPage mainPage = new MainPage();
    SalaryCalculatorPage salaryCalculatorPage = new SalaryCalculatorPage();

    @Given("I navigate to the {string} page")
    public void iNavigateToThePage(String page) {
        switch (page){
            case "home":
                mainPage.open();
                break;
            case "Salary Calculator":
                salaryCalculatorPage.open();
                break;

            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }


    @When("I fill out Search field with {string}")
    public void iFillOutFieldWith(String text) {
        mainPage.fillOutSearchField(text);
    }

    @Then("I submit the search form")
    public void iSubmitTheSearchForm() {
        mainPage.submitSearchForm();
    }

    @Then("Top search result contains text {string}")
    public void topSearchResultContainsText(String text) {
        WebElement element = mainPage.getTopSearchResult();
        String actualText = element.getText();
        assertThat(actualText).contains(text);
    }

    @Then("Click on a top search result")
    public void clickOnATopSearchResult() {
        mainPage.clickTopSearchResult();
    }

    @Then("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String field, String text) {
        salaryCalculatorPage.fillOutFieldWith(field, text);
    }

    @Then("I select {string} in the dropdown list")
    public void iSelectInTheDropdownList(String text) {
        WebElement perUnit = salaryCalculatorPage.getPerUnit();
        salaryCalculatorPage.selectDropdownByVisibleText(perUnit, text);
    }

    @When("I click on the Calculate button")
    public void iClickOnTheCalculateButton() {
        salaryCalculatorPage.ClickCalculateButton();
    }

    @Then("I should see {string} in {string} field")
    public void iShouldSeeInField(String text, String field) {
        String actualText = salaryCalculatorPage.getTextFromField(field);
        assertThat(actualText).contains(text);
    }
}
