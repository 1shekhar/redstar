/*This class would include Sign out tests from Embold when user is signed in with GitHub account*/
import CustomTestListener.TestReportListener;
import core.DataParser;
import core.Elemental;
import core.LoadEnvProps;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import java.io.IOException;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Embold Sign Out")
public class EmboldSignOutTestsForGitHub extends Elemental {
    EmboldSignInPage signInPage;
    GitHubSignInPage gitHubSignInPage;
    EmboldSignOutPanel signOutPage;
    RepositoryListPage repositoryListPage;

    EmboldSignOutTestsForGitHub() {
        super();
        signInPage = new EmboldSignInPage();
        gitHubSignInPage = new GitHubSignInPage();
        repositoryListPage = new RepositoryListPage();
        signOutPage = new EmboldSignOutPanel();
        try {
            locatorParser = new DataParser("./src/main/resources/props/Locators.properties");
            loadEnvProps = new LoadEnvProps();
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
        if (driver.getCurrentUrl().contains("auth")) {
            signInPage.DisplaySignInWithGitHubButton().click();
            try {
                Thread.sleep(2000);
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
        //Add proper assertion
        String currentTitle = driver.getTitle();
        String expectedTitle = "Embold | Next Level Static Code Analysis";
        Assert.assertEquals(currentTitle, expectedTitle);
    }
}
