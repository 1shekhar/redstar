package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class InstallEmboldAppGitHubPage extends Elemental {
    /*Click on authorize button is not happening. related issue is added here
    https://github.com/SeleniumHQ/selenium/issues/9398*/

    public WebElement DisplayEmboldOrgName()
    {
        FluentWaitForWebElement("embold_orgName");
        return driver.findElement(locatorParser.getElementLocator("embold_orgName"));
    }

    public WebElement DisplayVerifyIdentityText()
    {
        FluentWaitForWebElement("identity_text");
        return driver.findElement(locatorParser.getElementLocator("identity_text"));
    }

    public WebElement DisplayAuthorizeOrgButton()
    {
        FluentWaitForWebElement("authorize_button");
        return driver.findElement(locatorParser.getElementLocator("authorize_button"));
    }
}
