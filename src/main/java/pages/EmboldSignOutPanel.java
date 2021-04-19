package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class EmboldSignOutPanel extends Elemental {

    public EmboldSignOutPanel()
    {
        super();
    }

    public WebElement DisplaySignOutButton() {
        //WaitTillPresenceOfElementIsLocated("signOutButton");
        FluentWaitForWebElement("signOutButton");
        return driver.findElement(locatorParser.
                getElementLocator("signOutButton"));
    }

    public void ClickSignOutButton(String locator)
    {
        ClickOnWebElement(locator);
    }

}
