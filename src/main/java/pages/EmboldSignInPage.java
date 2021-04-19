package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class EmboldSignInPage extends Elemental {

    public WebElement DisplayEmboldLogo() {
        WaitTillPresenceOfElementIsLocated("embold_logo");
        return driver.findElement(locatorParser.
                getElementLocator("embold_logo"));
    }

    public WebElement DisplayLoginIllustration() {
        WaitTillPresenceOfElementIsLocated("login_illustration");
        return driver.findElement(locatorParser.
                getElementLocator("login_illustration"));
    }

    public WebElement DisplaySignInLabel() {
        WaitTillPresenceOfElementIsLocated("signIn_label");
        return driver.findElement(locatorParser.
                getElementLocator("signIn_label"));
    }

    public WebElement DisplaySignInWithGitHubButton() {
        FluentWaitForWebElement("github-sign-in-button");
        return driver.findElement(locatorParser.
                getElementLocator("github-sign-in-button"));
    }

    public WebElement DisplaySignInWithBitbucketButton() {
        WaitTillElementIsClickable("bitbucket-sign-in-button");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket-sign-in-button"));
    }

    public WebElement DisplaySignUpLink() {
        WaitTillElementIsClickable("signup_link");
        return driver.findElement(locatorParser.
                getElementLocator("signup_link"));
    }

}
