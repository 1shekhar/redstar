import CustomTestListener.TestReportListener;
import core.Elemental;
import core.LocatorParser;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import java.io.IOException;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Embold Sign Out")
public class EmboldSignOutTests extends Elemental {
    EmboldSignInPage signInPage;
    GitHubSignInPage gitHubSignInPage;
    BitbucketSignInPage bitbucketSignInPage;
    EmboldSignOutPanel signOutPage;
    RepositoryListPage repositoryListPage;

    EmboldSignOutTests() {
        super();
        signInPage = new EmboldSignInPage();
        gitHubSignInPage = new GitHubSignInPage();
        repositoryListPage = new RepositoryListPage();
        signOutPage = new EmboldSignOutPanel();
        bitbucketSignInPage = new BitbucketSignInPage();
        try {
            locatorParser = new LocatorParser("./src/main/resources/Locators.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Story("Sign out from Embold")
    @Test(priority = 1, description = "Validate Github Sign in and Sign out.")
    @Description("Test Description: Validate if user signs in and signs out from Embold using GitHub credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Sign in and sign out from Embold.")
    public void EmboldSignOutValidationForGitHubAccount() {
        if(driver.getCurrentUrl().contains("auth"))
        {
            signInPage.DisplaySignInWithGitHubButton().click();
            if (!driver.getCurrentUrl().contains("gh")) {
                String username = locatorParser.getSingularProperty("gh_username");
                String password = locatorParser.getSingularProperty("gh_password");
                gitHubSignInPage.signInToEmboldUsingGitHubCredentials(username, password);
            }
            ghSignInState=true;
        }
        if(ghSignInState)
        {
            repositoryListPage.DisplayUserAvatarInHeader().click();
            signOutPage.DisplaySignOutButton().click();
            ghSignInState=false;
        }
        //Add proper assertion
        String currentTitle = driver.getTitle();
        String expectedTitle = "Embold | Next Level Static Code Analysis";
        Assert.assertEquals(currentTitle, expectedTitle);
    }

    @Story("Sign out from Embold")
    @Test(priority = 2, description = "Validate Bitbucket Sign in and Sign out.")
    @Description("Test Description: Validate if user signs in and signs out from Embold using Bitbucket credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Sign in and sign out from Embold.")
    public void EmboldSignOutValidationForBitbucketAccount()  {
        if(driver.getCurrentUrl().contains("auth"))
        {
            signInPage.DisplaySignInWithBitbucketButton().click();
            try {
                /*Without sleep, enable to perform sign out. To do:Find alternative later.
                * This is happening only for BitBucket re-login*/
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!driver.getCurrentUrl().contains("/organization/bb")) {
                String username = locatorParser.getSingularProperty("bitbucket_username");
                String password = locatorParser.getSingularProperty("bitbucket_password");
                bitbucketSignInPage.signInToEmboldUsingBitbucketCredentials(username, password);
            }
            bbSignInState=true;
        }
        Assert.assertTrue(repositoryListPage.DisplayEmboldLogoOnRepositoryListPage().isDisplayed());
        if(bbSignInState)
        {
            repositoryListPage.DisplayUserAvatarInHeader().click();
            signOutPage.DisplaySignOutButton().click();
            bbSignInState=false;
        }
        //Add proper assertion
        String currentTitle = driver.getTitle();
        String expectedTitle = "Embold | Next Level Static Code Analysis";
        Assert.assertEquals(currentTitle, expectedTitle);
    }
}
