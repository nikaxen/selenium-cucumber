package pages.ck12;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

public class HeaderAndFooter extends Page {
    @FindBy(xpath = "//span[@data-i18n='c.h.sign.in']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='row collapse sign-in-button-wrapper']")
    private WebElement signInPopup;

    @FindBy(xpath = "//input[@id='searchBox']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='hide-for-small hide-for-medium sc-epnACN eyjVxf']//div//i[@title='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='search-header-query']/span[2]")
    private WebElement searchQueryHeader;

    @FindBy(xpath = "//a[@class='goog-te-menu-value']")
    private WebElement googleTranslateWidgetButton;

    @FindBy(xpath = "//iframe[@title='Language Translate Widget']")
    private WebElement googleTranslateWidgetIframe;

    @FindBy(xpath = "//span[normalize-space()='Select Language']")
    private WebElement googleTranslateWidgetSelectLanguageSpan;

    @FindBy(xpath = "//footer[@id='footer']")
    private WebElement footer;

    public void clickOnSignInButton(){
        waitForClickable(signInButton);
        click(signInButton);
    }
    public boolean signInIsVisible(){
        waitForVisible(signInPopup);
        if(signInPopup.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    public void clickOnGoogleTranslateWidgetButton(){
        waitForVisible(googleTranslateWidgetButton);
        click(googleTranslateWidgetButton);
    }

    public boolean googleTranslateWidgetIframeIsVisible(){
        waitForVisible(googleTranslateWidgetIframe);
        return googleTranslateWidgetIframe.isDisplayed();
    }

    public void switchToGoogleTranslateIframe(){
        switchToIframe(googleTranslateWidgetIframe);
    }

    public String getGoogleTranslateSelectLanguageSpanText(){
        waitForVisible(googleTranslateWidgetSelectLanguageSpan);
        return googleTranslateWidgetSelectLanguageSpan.getText();
    }

    public void googleTranslateSetLanguage(String language){
        WebElement element = getByXPath("//span[normalize-space()='" + language + "']");
        click(element);
    }

    public boolean verifyFooterPresence(){
        waitForVisible(footer);
        return footer.isDisplayed();
    }

    public void typeTextToSearchFieldInHeader(String text){
        waitForClickable(searchInput);
        sendKeys(searchInput, text);
    }

    public void clickOnTheSearchButton(){
        waitForClickable(searchButton);
        click(searchButton);
    }

    public String getSearchQueryHeaderText(){
        waitForVisible(searchQueryHeader);
        return searchQueryHeader.getText();
    }

    public void scrollToTheFooter(){
        waitForVisible(footer);
        scrollToElement(footer);
    }
    public void scrollToTheFooter(int offset){
        waitForVisible(footer);
        scrollToElement(footer, offset);
    }
}
