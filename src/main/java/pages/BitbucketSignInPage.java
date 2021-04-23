package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class BitbucketSignInPage extends Elemental {

    public WebElement DisplayBitbucketUsernameField() {
        FluentWaitForWebElement("bitbucket_username_field");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket_username_field"));
    }

    public WebElement DisplayBitbucketContinueButton() {
        WaitTillElementIsClickable("bitbucket_continue_button");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket_continue_button"));
    }

    public WebElement DisplayBitbucketPasswordField() {
        WaitTillPresenceOfElementIsLocated("bitbucket_password_field");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket_password_field"));
    }

    public WebElement DisplayBitbucketLoginButton() {
        WaitTillPresenceOfElementIsLocated("bitbucket_login_button");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket_login_button"));
    }

    public void signInToEmboldUsingBitbucketCredentials(String username, String password)
    {
        DisplayBitbucketUsernameField().sendKeys(username);
        DisplayBitbucketContinueButton().click();
        WaitTillTextFieldIsReady("bitbucket_password_field");
        DisplayBitbucketPasswordField().sendKeys(password);
        DisplayBitbucketLoginButton().click();
        /*To DO: Logic to validate API response. Sometimes App is up but DB/Node gets crashed and user is
        unable to login to Embold. Can be validated using API endpoint.*/
        WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
    }
}
