package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class RepositoryListPage extends Elemental {
    public WebElement DisplayUserAvatarInHeader() {
        WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
        return driver.findElement(locatorParser.
                getElementLocator("loggedInUserAvatar"));
    }

    public WebElement DisplayEmboldLogoOnRepositoryListPage()
    {
        WaitTillPresenceOfElementIsLocated("embold_logo_RLPage");
        return driver.findElement(locatorParser.
                getElementLocator("embold_logo_RLPage"));
    }
}
