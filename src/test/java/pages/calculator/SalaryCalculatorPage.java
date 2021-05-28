package pages.calculator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

public class SalaryCalculatorPage extends Page {

    public SalaryCalculatorPage(){
        setUrl("https://www.calculator.net/salary-calculator.html");
    }

    @FindBy(xpath = "//input[@id='camount']")
    private WebElement salaryAmount;

    @FindBy(xpath = "//select[@name='cunit']")
    private WebElement perUnit;

    @FindBy(xpath = "//input[@id='chours']")
    private WebElement hoursPerWeek;

    @FindBy(xpath = "//input[@id='cdays']")
    private WebElement daysPerWeek;

    @FindBy(xpath = "//input[@id='cholidays']")
    private WebElement holidaysPerYear;

    @FindBy(xpath = "//input[@id='cvacation']")
    private WebElement vacationDaysPerYear;

    @FindBy(xpath = "//input[@value='Calculate']")
    private WebElement calculateButton;

    @FindBy(xpath = "//tbody/tr[9]/td[2]")
    private WebElement unadjestedAnnualAmount;

    @FindBy(xpath = "//tbody/tr[9]/td[3]")
    private WebElement adjastedAnnualAmount;

    public void fillOutFieldWith(String field, String text){
        switch (field){
            case "Salary Amount":
                fillOutFieldWith(salaryAmount, text);
                break;
            case "Hours per Week":
                fillOutFieldWith(hoursPerWeek, text);
                break;
            case "Days per Week":
                fillOutFieldWith(daysPerWeek, text);
                break;
            case "Holidays per Year":
                fillOutFieldWith(holidaysPerYear, text);
                break;
            case "Vacation Days per Year":
                fillOutFieldWith(vacationDaysPerYear, text);
                break;
            default:
                throw new RuntimeException("Unknown field " + field);
        }
    }

    public void fillOutFieldWith(WebElement element, String text){
        // clearElement(element);
        waitForClickable(element);
        sendKeys(element, text);
    }

    public WebElement getPerUnit(){
        return perUnit;
    }

    public void ClickCalculateButton(){
        waitForClickable(calculateButton);
        click(calculateButton);
    }

    public String getTextFromField(String field){
        return switch (field) {
            case "Unadjusted" -> unadjestedAnnualAmount.getText();
            case "Adjusted" -> adjastedAnnualAmount.getText();
            default -> throw new RuntimeException("Unknown field" + field);
        };
    }
}
