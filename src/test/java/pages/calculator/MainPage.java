package pages.calculator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

public class MainPage extends Page {
    public MainPage(){
        setUrl("https://www.calculator.net/");
    }

    @FindBy(xpath = "//a[text()='Salary Calculator']")
    private WebElement salaryCalcLink;

    @FindBy(xpath = "//input[@id='calcSearchTerm']")
    private WebElement searchField;

    @FindBy(xpath = "//span[@id='bluebtn']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='calcSearchOut']//div//a")
    private WebElement topSearchResult;

    public void fillOutSearchField(String text){
        waitForClickable(searchField);
        sendKeys(searchField, text);
    }
    public void submitSearchForm(){
        click(searchButton);
    }

    public WebElement getTopSearchResult(){
        waitForClickable(topSearchResult);
        return topSearchResult;
    }

    public void clickTopSearchResult(){
        click(topSearchResult);
    }
}
