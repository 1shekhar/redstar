package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class EmboldSignOutPanel extends Elemental {

    public EmboldSignOutPanel()
    {
        super();
    }

    public WebElement DisplaySignOutButton() {
        WaitTillPresenceOfElementIsLocated("signOutButton");
        return driver.findElement(locatorParser.
                getElementLocator("signOutButton"));
    }


}
