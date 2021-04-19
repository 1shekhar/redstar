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

    public void signInToEmboldUsingGitHubCredentials(String username, String password)
    {
        /*To do: 1. Encrypt and provide credentials. 2. Pass credentials via commandline
        3. Simplify Login with independent singular methods.*/
        ProvideGitHubCredentials(username, password);
         /*To DO: Logic to validate API response. Sometimes App is up but DB/Node gets crashed and user is
        unable to login to Embold. Can be validated using API endpoint.*/
        WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
    }

    public void ProvideGitHubCredentials(String username, String password)
    {
        DisplayGitHubUsernameField().sendKeys(username);
        DisplayGitHubPasswordField().sendKeys(password);
        DisplayGitHubSignInButton().click();
    }

}
