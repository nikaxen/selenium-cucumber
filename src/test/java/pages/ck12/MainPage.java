package pages.ck12;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends HeaderAndFooter {
    public MainPage(){
        setUrl("https://www.ck12.org/");
    }

    @FindBy(xpath = "//a[@href='#science']//span")
    private WebElement scienceSpan;

    public String getScienceSpanText(){
        waitForVisible(scienceSpan);
        return scienceSpan.getText();
    }
}
