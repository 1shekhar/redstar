import CustomTestListener.TestReportListener;
import core.Elemental;
import core.DataParser;
import core.LoadEnvProps;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.IOException;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Sign In to Embold")
public class EmboldSignInPageTests extends Elemental {
    static WebElement element;
    EmboldSignInPage signInPage;
    GitHubSignInPage gitHubSignInPage;
    BitbucketSignInPage bitbucketSignInPage;
    RepositoryListPage repositoryListPage;
    EmboldSignOutPanel signOutPage;

    EmboldSignInPageTests() {
        super();
        signInPage = new EmboldSignInPage();
        gitHubSignInPage = new GitHubSignInPage();
        repositoryListPage = new RepositoryListPage();
        bitbucketSignInPage = new BitbucketSignInPage();
        signOutPage = new EmboldSignOutPanel();
        try {
            locatorParser = new DataParser("./src/main/resources/props/Locators.properties");
            loadEnvProps = new LoadEnvProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 1,description = "Validate page title - Sign In page.")
    @Description("Test Description: Page title for Sign In page should be 'Embold | Next Level Static Code Analysis'")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if page title shown is correct for Embold Sign In page.")
    public void EmboldSignInPageTitleIsDisplayed() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Embold | Next Level Static Code Analysis");
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 2, description = "Validate if login illustration is displayed.")
    @Description("Test Description: Login illustration should be displayed within Sign In window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if login illustration is displayed in Sign In window.")
    public void LoginIllustrationIsDisplayed() {
        element = signInPage.DisplayLoginIllustration();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 3, description = "Validate if Embold logo is displayed.")
    @Description("Test Description: Embold logo is displayed on the top left corner of the page.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Embold logo is displayed on top left corner of the page.")
    public void EmboldLogoIsDisplayed() {
        element = signInPage.DisplayEmboldLogo();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 4, description = "Validate if Sign In Label is displayed.")
    @Description("Test Description: Sign In Label should be displayed above login window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Sign In label is displayed above Sign In window.")
    public void SignInLabelIsDisplayed() {
        element = signInPage.DisplaySignInLabel();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 5, description = "Validate if Sign In with GitHub button is displayed.")
    @Description("Test Description: Sign In with GitHub button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with GitHub button is displayed in Sign In window.")
    public void SignInWithGitHubButtonIsDisplayed() {
        element = signInPage.DisplaySignInWithGitHubButton();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 6, description = "Validate if Sign In with Bitbucket button is displayed.")
    @Description("Test Description: Sign In with Bitbucket button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with Bitbucket button is displayed in Sign In window.")
    public void SignInWithBitbucketButtonIsDisplayed() {
        element = signInPage.DisplaySignInWithBitbucketButton();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 7, description = "Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign up link is displayed below Sign In window.")
    public void SignUpLinkIsDisplayed() {
        element = signInPage.DisplaySignUpLink();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign in using GitHub")
    @Test(priority = 8, description = "Validate browser back button for Sign In page.")
    @Description("Test Description: User should land on Sign In page.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Check if User is landed on Sign In page.")
    public void ClickOnSignInWithGitHubButtonAndReturnToEmboldSignInPage() {
        if(signInPage.DisplaySignInWithGitHubButton().isDisplayed())
        {
            signInPage.DisplaySignInWithGitHubButton().click();
            WaitTillElementIsClickable("github_signIn_button");
            Assert.assertEquals(driver.getTitle(), "Sign in to GitHub · GitHub");
            driver.navigate().back();
            WaitTillElementIsClickable("github-sign-in-button");
            driver.navigate().refresh();
            Assert.assertEquals(driver.getTitle(), "Embold | Next Level Static Code Analysis");
        }
    }

    @Story("Sign in using GitHub")
    @Test(priority = 9, description = "Validate if user is able to Sign in to Embold using GitHub account credentials.")
    @Description("Test Description: User should be able to Sign in to Embold using GitHub account credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if user is able to Sign in to Embold using GitHub account credentials.")
    public void SignInToEmboldUsingGitHubAccountCredentials() {
        if (driver.getCurrentUrl().contains("auth")) {
            signInPage.DisplaySignInWithGitHubButton().click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!driver.getCurrentUrl().contains("/organization/gh/")) {
                gitHubSignInPage.ProvideGitHubCredentials(getPropertyValue("ghuser"),
                        getPropertyValue("ghpass"));
            }
            ghSignInState = true;
            WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
        }
        Assert.assertTrue(repositoryListPage.DisplayEmboldLogoOnRepositoryListPage().isDisplayed());

        /*Logout is added for chained test cases*/
        if (ghSignInState) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repositoryListPage.DisplayUserAvatarInHeader().click();
            signOutPage.DisplaySignOutButton().click();
            WaitTillPresenceOfElementIsLocated("login_illustration");
            ghSignInState = false;
        }
    }

    @Story("Sign in using Bitbucket")
    @Test(priority = 10, description = "Validate if user is able to Sign in to Embold using Bitbucket account credentials.")
    @Description("Test Description: User should be able to Sign in to Embold using Bitbucket account credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if user is able to Sign in to Embold using Bitbucket account credentials.")
    public void SignInToEmboldUsingBitbucketAccountCredentials() {
        if (driver.getCurrentUrl().contains("auth")) {
            signInPage.DisplaySignInWithBitbucketButton().click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!driver.getCurrentUrl().contains("/organization/bb")) {
                bitbucketSignInPage.signInToEmboldUsingBitbucketCredentials(
                        getPropertyValue("bbuser"),
                        getPropertyValue("bbpass"));
            }
            bbSignInState = true;
            WaitTillPresenceOfElementIsLocated("loggedInUserAvatar");
        }
        Assert.assertTrue(repositoryListPage.DisplayEmboldLogoOnRepositoryListPage().isDisplayed());

        /*Logout is added for chained test cases*/
        if (bbSignInState) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repositoryListPage.DisplayUserAvatarInHeader().click();
            signOutPage.DisplaySignOutButton().click();
            WaitTillPresenceOfElementIsLocated("login_illustration");
            bbSignInState = false;
        }
    }
}