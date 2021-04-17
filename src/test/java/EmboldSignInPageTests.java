import CustomTestListener.TestReportListener;
import core.Elemental;
import core.LocatorParser;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EmboldSignInPage;
import pages.GitHubSignInPage;
import pages.RepositoryListPage;
import java.io.IOException;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Sign In to Embold")
public class EmboldSignInPageTests extends Elemental {
    static WebElement element;
    EmboldSignInPage signInPage;
    GitHubSignInPage gitHubSignInPage;
    RepositoryListPage repositoryListPage;

    EmboldSignInPageTests() {
        super();
        signInPage = new EmboldSignInPage();
        gitHubSignInPage = new GitHubSignInPage();
        repositoryListPage = new RepositoryListPage();
        try {
            locatorParser = new LocatorParser("./src/main/resources/Locators.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate page title - Sign In page.")
    @Description("Test Description: Page title for Sign In page should be 'Embold | Next Level Static Code Analysis'")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if page title shown is correct for Embold Sign In page.")
    public void EmboldSignInPageTitleIsDisplayed() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Embold | Next Level Static Code Analysis");
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 1, description = "Validate if login illustration is displayed.")
    @Description("Test Description: Login illustration should be displayed within Sign In window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if login illustration is displayed in Sign In window.")
    public void LoginIllustrationIsDisplayed() {
        element = signInPage.DisplayLoginIllustration();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 2, description = "Validate if Embold logo is displayed.")
    @Description("Test Description: Embold logo is displayed on the top left corner of the page.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Embold logo is displayed on top left corner of the page.")
    public void EmboldLogoIsDisplayed() {
        element = signInPage.DisplayEmboldLogo();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 3, description = "Validate if Sign In Label is displayed.")
    @Description("Test Description: Sign In Label should be displayed above login window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Sign In label is displayed above Sign In window.")
    public void SignInLabelIsDisplayed() {
        element = signInPage.DisplaySignInLabel();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 4, description = "Validate if Sign In with GitHub button is displayed.")
    @Description("Test Description: Sign In with GitHub button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with GitHub button is displayed in Sign In window.")
    public void SignInWithGitHubButtonIsDisplayed() {
        element = signInPage.DisplaySignInWithGitHubButton();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 5, description = "Validate if Sign In with Bitbucket button is displayed.")
    @Description("Test Description: Sign In with Bitbucket button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with Bitbucket button is displayed in Sign In window.")
    public void SignInWithBitbucketButtonIsDisplayed() {
        element = signInPage.DisplaySignInWithBitbucketButton();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign In page Web element validations")
    @Test(priority = 6, description = "Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign up link is displayed below Sign In window.")
    public void SignUpLinkIsDisplayed() {
        element = signInPage.DisplaySignUpLink();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign in using GitHub")
    @Test(priority = 7, description = "Validate browser back button for Sign In page.")
    @Description("Test Description: User should land on Sign In page.")
    @Severity(SeverityLevel.NORMAL)
    @Step("Check if User is landed on Sign In page.")
    public void ClickOnSignInWithGitHubButtonAndReturnToEmboldSignInPage() {
        signInPage.DisplaySignInWithGitHubButton().click();
        WaitTillElementIsClickable("github_signIn_button");
        Assert.assertEquals(driver.getTitle(), "Sign in to GitHub · GitHub");
        driver.navigate().back();
        WaitTillElementIsClickable("github-sign-in-button");
        driver.navigate().refresh();
        Assert.assertEquals(driver.getTitle(), "Embold | Next Level Static Code Analysis");
    }

    @Story("Sign in using GitHub")
    @Test(priority = 8, description = "Validate if user is able to Sign in to Embold using GitHub account credentials.")
    @Description("Test Description: User should be able to Sign in to Embold using GitHub account credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if user is able to Sign in to Embold using GitHub account credentials.")
    public void SignInToEmboldUsingGitHubAccountCredentials() {
        signInPage.DisplaySignInWithGitHubButton().click();
        WaitTillElementIsClickable("github_signIn_button");

        //To do: 1. Encrypt and provide credentials. 2. Pass credentials via commandline
        //3. Simplify Login with independent singular methods.
        String username=locatorParser.getSingularProperty("gh_username");
        String password=locatorParser.getSingularProperty("gh_password");

        gitHubSignInPage.signInToEmboldUsingGitHubCredentials(username, password);
        gitHubSignInPage.DisplayGitHubSignInButton().click();
        //To DO: Logic to validate API response. Sometimes App is up but DB/Node gets crashed and user is
        //unable to login to Embold. Can be validated using API endpoint.
        String currentTitle=driver.getTitle();
        String expectedTitle="Embold | Next Level Static Code Analysis";
        Assert.assertEquals(currentTitle, expectedTitle);
        if(currentTitle.equals(expectedTitle))
        {
            pageState=true;
        }
    }

    @Story("Sign in using GitHub")
    @Test(priority = 9, description = "Validate if user signs out from Embold.", dependsOnMethods = {"SignInToEmboldUsingGitHubAccountCredentials"})
    @Description("Test Description: Validate if user signs out from Embold and redirected to Sing in page.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Sign out from Embold.")
    public void SignOutFromEmbold() {
        //Embold Sign out works totally after this boolean condition. Fails sometimes without it. Find root cause later.
        if(pageState) {
            WaitTillPresenceOfElementIsLocated("embold_logo_RLPage");
            repositoryListPage.DisplayUserAvatarInHeader().click();
            repositoryListPage.DisplaySignOutButton().click();
            WebElement element = signInPage.DisplayLoginIllustration();
            Assert.assertTrue(element.isDisplayed());
        }
    }
}