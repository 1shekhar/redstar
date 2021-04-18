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
@Feature("Sign out from Embold")
public class EmboldSignOutTests extends Elemental {
    EmboldSignInPage signInPage1;
    GitHubSignInPage gitHubSignInPage1;
    BitbucketSignInPage bitbucketSignInPage1;
    EmboldSignOutPanel signOutPage1;
    RepositoryListPage repositoryListPage1;

    EmboldSignOutTests() {
        super();
        signInPage1 = new EmboldSignInPage();
        gitHubSignInPage1 = new GitHubSignInPage();
        repositoryListPage1 = new RepositoryListPage();
        signOutPage1 = new EmboldSignOutPanel();
        bitbucketSignInPage1 = new BitbucketSignInPage();
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
        if (signInPage1.DisplaySignInWithGitHubButton().isDisplayed()) {
            signInPage1.DisplaySignInWithGitHubButton().click();
            WaitTillElementIsClickable("github_signIn_button");
            String username = locatorParser.getSingularProperty("gh_username");
            String password = locatorParser.getSingularProperty("gh_password");
            gitHubSignInPage1.signInToEmboldUsingGitHubCredentials(username, password);
            repositoryListPage1.DisplayUserAvatarInHeader().click();
            signOutPage1.DisplaySignOutButton().click();
            WaitTillPresenceOfElementIsLocated("login_illustration");
            //Add proper assertion
            String currentTitle = driver.getTitle();
            String expectedTitle = "Embold | Next Level Static Code Analysis";
            Assert.assertEquals(currentTitle, expectedTitle);
        }
    }

    @Story("Sign out from Embold")
    @Test(priority = 2, description = "Validate Bitbucket Sign in and Sign out.")
    @Description("Test Description: Validate if user signs in and signs out from Embold using Bitbucket credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Sign in and sign out from Embold.")
    public void EmboldSignOutValidationForBitbucketAccount() {
        if (signInPage1.DisplaySignInWithBitbucketButton().isDisplayed()) {
            signInPage1.DisplaySignInWithBitbucketButton().click();
            String username = locatorParser.getSingularProperty("bitbucket_username");
            String password = locatorParser.getSingularProperty("bitbucket_password");
            bitbucketSignInPage1.signInToEmboldUsingBitbucketCredentials(username, password);
            repositoryListPage1.DisplayUserAvatarInHeader().click();
            signOutPage1.DisplaySignOutButton().click();
            WaitTillPresenceOfElementIsLocated("login_illustration");
            //Add proper assertion
            String currentTitle = driver.getTitle();
            String expectedTitle = "Embold | Next Level Static Code Analysis";
            Assert.assertEquals(currentTitle, expectedTitle);
        }
    }
}
