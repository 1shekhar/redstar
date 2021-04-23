package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class GitHubSignInPage extends Elemental {
    public WebElement DisplayGitHubUsernameField() {
        WaitTillPresenceOfElementIsLocated("github_username_field");
        return driver.findElement(locatorParser.
                getElementLocator("github_username_field"));
    }

    public WebElement DisplayGitHubPasswordField() {
        WaitTillPresenceOfElementIsLocated("github_password_field");
        return driver.findElement(locatorParser.
                getElementLocator("github_password_field"));
    }

    public WebElement DisplayGitHubSignInButton() {
        WaitTillPresenceOfElementIsLocated("github_signIn_button");
        return driver.findElement(locatorParser.
                getElementLocator("github_signIn_button"));
    }

    public void ProvideGitHubCredentials(String username, String password)
    {
        DisplayGitHubUsernameField().sendKeys(username);
        DisplayGitHubPasswordField().sendKeys(password);
        FluentWaitForWebElement("github_signIn_button");
        DisplayGitHubSignInButton().click();
        //WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
    }

}
