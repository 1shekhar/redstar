package pages;

import core.Elemental;
import org.openqa.selenium.WebElement;

public class EmboldSignUpPage extends Elemental {

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

    public WebElement DisplaySignUpLabel() {
        WaitTillPresenceOfElementIsLocated("signUp_label");
        return driver.findElement(locatorParser.
                getElementLocator("signUp_label"));
    }

    public WebElement DisplaySignUpWithGitHubButton() {
        WaitTillElementIsClickable("github-sign-in-button");
        return driver.findElement(locatorParser.
                getElementLocator("github-sign-in-button"));
    }

    public WebElement DisplaySignUpWithBitbucketButton() {
        WaitTillElementIsClickable("bitbucket-sign-in-button");
        return driver.findElement(locatorParser.
                getElementLocator("bitbucket-sign-in-button"));
    }

    public WebElement DisplaySignInLink() {
        WaitTillElementIsClickable("signIn_link");
        return driver.findElement(locatorParser.
                getElementLocator("signIn_link"));
    }

    public void OpenSignUpPage()
    {
        EmboldSignInPage signInPage = new EmboldSignInPage();
        signInPage.DisplaySignUpLink().click();

    }
}
