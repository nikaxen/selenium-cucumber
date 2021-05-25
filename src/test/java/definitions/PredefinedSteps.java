package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.getDriver;

public class PredefinedSteps {

    @Given("Navigate to {string}")
    public void navigateTo(String url){
        getDriver().get(url);
    }

    @And("Resize window to {int} and {int}")
    public void resizeWindowToAnd(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        getDriver().manage().window().setSize(dimension);
    }

    @And("Wait for {int} sec")
    public void waitForSec(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    @And("Maximize window")
    public void maximizeWindow() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension maxWindowSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        getDriver().manage().window().setPosition(new Point(0,0));
        getDriver().manage().window().setSize(maxWindowSize);
    }

    @Then("Element with XPath {string} should be present")
    public void elementWithXpathShouldBePresent(String xpath){
        assertThat(getDriver().findElements(By.xpath(xpath))).hasSize(1);
    }

    @Then("Element with XPath {string} should not be present")
    public void elementWithXpathShouldNotBePresent(String xpath){
        assertThat(getDriver().findElements(By.xpath(xpath))).hasSize(0);
    }

    @Then("Wait for element with XPath {string} to be present")
    public void waitForElementWithXpathToBePresent(String xpath) {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    @Then("Wait for element with XPath {string} to not be present")
    public void waitForElementWithXpathToNotBePresent(String xpath) {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
    }

    @Then("Element with XPath {string} should be displayed")
    public void elementWithXpathShouldBeDisplayed(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isDisplayed()).isTrue();
    }

    @Then("Element with XPath {string} should not be displayed")
    public void elementWithXpathShouldNotBeDisplayed(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isDisplayed()).isFalse();
    }

    @Then("Element with XPath {string} should be enabled")
    public void elementWithXpathShouldBeEnabled(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isEnabled()).isTrue();
    }

    @Then("Element with XPath {string} should be disabled")
    public void elementWithXpathShouldBeDisabled(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isEnabled()).isFalse();
    }

    @Then("Element with XPath {string} should be selected")
    public void elementWithXpathShouldBeSelected(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isSelected()).isTrue();
    }

    @Then("Element with XPath {string} should not be selected")
    public void elementWithXpathShouldNotBeSelected(String xpath) {
        assertThat(getDriver().findElement(By.xpath(xpath)).isSelected()).isFalse();
    }

    @When("Type {string} into element with xpath {string}")
    public void typeIntoElementWithXpath(String text, String xpath) {
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.sendKeys(text);
    }

    @When("Click on element with XPath {string}")
    public void clickOnElementWithXpath(String xpath) {
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }

    @When("Click on element using JavaScript with XPath {string}")
    public void iClickOnElementUsingJavaScriptWithXpath(String xpath) {
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click();", element);
    }
    @Then("Take screenshot")
    public void iTakeScreenshot() throws Exception {
        TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("target/screenshot " + new Date() + ".png"));
    }

    @Then("Element with XPath {string} should have text as {string}")
    public void elementWithXpathShouldHaveTextAs(String xpath, String text) {
        String actualText = getDriver().findElement(By.xpath(xpath)).getText();
        assertThat(actualText).isEqualTo(text);
    }

    @Then("Element with XPath {string} should not have text as {string}")
    public void elementWithXpathShouldNotHaveTextAs(String xpath, String text) {
        String actualText = getDriver().findElement(By.xpath(xpath)).getText();
        assertThat(actualText).isNotEqualTo(text);
    }

    @Then("Element with XPath {string} should contain text {string}")
    public void elementWithXpathShouldContainText(String xpath, String text) {
        String actualText = getDriver().findElement(By.xpath(xpath)).getText();
        assertThat(actualText).containsIgnoringCase(text);
    }

    @Then("Element with XPath {string} should not contain text {string}")
    public void elementWithXpathShouldNotContainText(String xpath, String text) {
        String actualText = getDriver().findElement(By.xpath(xpath)).getText();
        assertThat(actualText).doesNotContain(text);
    }

    @Then("Element with XPath {string} should have attribute {string} as {string}")
    public void elementWithXpathShouldHaveAttributeAs(String xpath, String attribute, String attributeValue) {
        assertThat(getDriver().findElement(By.xpath(xpath)).getAttribute(attribute)).isEqualTo(attributeValue);
    }

    @Then("Element with XPath {string} should not have attribute {string} as {string}")
    public void elementWithXpathShouldNotHaveAttributeAs(String xpath, String attribute, String attributeValue) {
        assertThat(getDriver().findElement(By.xpath(xpath)).getAttribute(attribute)).isNotEqualTo(attributeValue);
    }

    @Then("Switch to iframe with XPath {string}")
    public void switchToIframeWithXpath(String xpath) {
        getDriver().switchTo().frame(getDriver().findElement(By.xpath(xpath)));
    }

    @Then("Switch to default content")
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    @Then("Switch to new window")
    public void switchToNewWindow() {
        Iterator<String> iterator = getDriver().getWindowHandles().iterator();
        String newWindow = iterator.next();
        while(iterator.hasNext()) {
            newWindow = iterator.next();
        }
        getDriver().switchTo().window(newWindow);
    }

    @Then("Switch to first window")
    public void switchToFirstWindow() {
        getDriver().switchTo().window(getDriver().getWindowHandles().iterator().next());
    }

    @Then("Accept alert")
    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    @Then("Dismiss alert")
    public void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    @When("Clear element with XPath {string}")
    public void clearElementWithXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).clear();
    }

    @Then("Page title is {string}")
    public void pageTitleIs(String title) {
        assertThat(getDriver().getTitle()).isEqualTo(title);
    }

    @Then("Page title is not {string}")
    public void pageTitleIsNot(String title) {
        assertThat(getDriver().getTitle()).isNotEqualTo(title);
    }

    @Then("Page title contains {string}")
    public void pageTitleContains(String title) {
        assertThat(getDriver().getTitle()).contains(title);
    }
    @Then("Page title doesn't contain {string}")
    public void pageTitleDoesntContain(String title) {
        assertThat(getDriver().getTitle()).doesNotContain(title);
    }
    @When("Scroll to the element with XPath {string} with offset {int}")
    public void scrollToTheElementWithXpathWithOffset(String xpath, int offset) {
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(false);", element);
        executor.executeScript("window.scrollBy(0, " + offset + ");", element);
    }

    @When("Hover over element with XPath {string}")
    public void hoverOverElementWithXpath(String xpath) {
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        new Actions(getDriver()).moveToElement(element).perform();
    }

    @And("Refresh page")
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    @Then("Check if Checkbox with XPath {string} is checked")
    public void checkIfCheckboxWithXPathIsChecked(String xpath) {
        WebElement checkbox = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        assertThat(checkbox.isSelected()).isTrue();
    }
    @Then("Check if Checkbox with XPath {string} is not checked")
    public void checkIfCheckboxWithXPathIsNotChecked(String xpath) {
        WebElement checkbox = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        assertThat(checkbox.isSelected()).isFalse();
    }

    @And("Select the text {string} in dropdown dropdown list {string}")
    public void selectVisibleTextInDropdown(String text, String xpath){
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    @And("Select the value {string} in dropdown dropdown list {string}")
    public void selectValueTextInDropdown(String value, String xpath){
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    @Then("Find all broken links on the page of the website {string}")
    public void findBrokenLinksOnThePage(String homepage) {
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        String url = "";
        String link_text = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        Iterator<WebElement> it = links.iterator();
        WebElement it_link;
        while(it.hasNext()){
            it_link = it.next();
            url = it_link.getAttribute("href");
            link_text = it_link.getText();
            if(url == null || url.isEmpty()){
                System.out.println("["+ link_text + "] URL is either not configured or it's empty.");
                continue;
            }
            if(!url.startsWith(homepage)){
                System.out.println("["+ link_text + "] URL belongs to another domain, skipping it.");
                continue;
            }
            try{
                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if(respCode>=400){
                    System.out.println(url + " is a BROKEN link");
                }else{
                    System.out.println(url + " is a valid link");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
