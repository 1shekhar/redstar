package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class TermsAndServicePage extends Elemental {

    public WebElement DisplayEmboldLogo()
    {
        FluentWaitForWebElement("terms_Service_embold_logo");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_embold_logo"));
    }

    public WebElement DisplayServiceIllustration()
    {
        FluentWaitForWebElement("terms_service_illustration");
        return driver.findElement(locatorParser.
                getElementLocator("terms_service_illustration"));
    }

    public WebElement DisplayTermsAndServiceLabel()
    {
        FluentWaitForWebElement("terms_Service_label");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_label"));
    }

    public WebElement DisplayConsentCheckbox()
    {
        FluentWaitForWebElement("terms_Service_checkbox");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_checkbox"));
    }

    public WebElement DisplayConsentText()
    {
        FluentWaitForWebElement("terms_Service_AgreeText");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_AgreeText"));
    }

    public WebElement DisplayConsentLink()
    {
        FluentWaitForWebElement("terms_Service_link");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_link"));
    }

    public WebElement DisplaySubmitButton()
    {
        FluentWaitForWebElement("terms_Service_NextButton");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_NextButton"));
    }

    public WebElement DisplayTermsAndServiceDescription()
    {
        FluentWaitForWebElement("terms_Service_description");
        return driver.findElement(locatorParser.
                getElementLocator("terms_Service_description"));
    }
}
