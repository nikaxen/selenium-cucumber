package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import static support.TestContext.getDriver;

public class Page {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page(){
        PageFactory.initElements(getDriver(), this);
    }

    public void open(){
        getDriver().get(url);
    }

    public WebElement getByXPath(String xpath){
        WebElement element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public void moveToElement(WebElement element){
        new Actions(getDriver()).moveToElement(element).perform();
    }

    public void waitForVisible(WebElement element){
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForNotVisible(WebElement element){
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForClickable(WebElement element){
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element){
        waitForClickable(element);
        try{
            element.click();
        }catch (WebDriverException e){
            clickWithJS(element);
        }
    }

    public void clickWithJS(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click();", element);
    }

    public void sendKeys(WebElement element, String text){
        waitForVisible(element);
        element.sendKeys(text);
    }
    public void sendKeys(WebElement element){
        waitForVisible(element);
        element.sendKeys(Keys.RETURN);
    }

    public void clearElement(WebElement element){
        waitForVisible(element);
        element.clear();
    }

    public void selectDropdownByVisibleText(WebElement element, String text){
        waitForClickable(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectDropdownByValue(WebElement element, String val){
        waitForClickable(element);
        Select select = new Select(element);
        select.selectByValue(val);
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(false);", element);
        jse.executeScript("window.scrollBy(0, 0);", element);
    }

    public void scrollToElement(WebElement element, int offset){
        /* Scroll with offset */
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(false);", element);
        jse.executeScript("window.scrollBy(0, " + offset + ");", element);
    }

    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void switchToIframe(WebElement element){
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void switchToDefaultContent(){
        getDriver().switchTo().defaultContent();
    }

    public void switchToNewWindow(){
        Iterator<String> iterator = getDriver().getWindowHandles().iterator();
        String newWindow = iterator.next();
        while (iterator.hasNext()){
            newWindow = iterator.next();
        }
        getDriver().switchTo().window(newWindow);
    }

    public void switchToFirstWindow(){
        getDriver().getWindowHandles().iterator().next();
    }

    public void acceptAlert(){
        getDriver().switchTo().alert().accept();
    }

    public void dismissAlert(){
        getDriver().switchTo().alert().dismiss();
    }

    public void takeScreenshot() throws IOException {
        TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("target/screenshot " + new Date() + ".png"));
    }

    public void wait(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
}
