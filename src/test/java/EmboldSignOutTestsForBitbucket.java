/*This class would include Sign out tests from Embold when user is signed in with GitHub account*/
import CustomTestListener.TestReportListener;
import core.Elemental;
import core.DataParser;
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
public class EmboldSignOutTestsForBitbucket extends Elemental {
    EmboldSignInPage signInPage;
    BitbucketSignInPage bitbucketSignInPage;
    EmboldSignOutPanel signOutPage;
    RepositoryListPage repositoryListPage;

    EmboldSignOutTestsForBitbucket() {
        super();
        signInPage = new EmboldSignInPage();
        repositoryListPage = new RepositoryListPage();
        signOutPage = new EmboldSignOutPanel();
        bitbucketSignInPage = new BitbucketSignInPage();
        try {
            locatorParser = new DataParser("./src/main/resources/props/Locators.properties");
            loadEnvProps = new LoadEnvProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Story("Sign out from Embold")
    @Test(priority = 1, description = "Validate Bitbucket Sign in and Sign out.")
    @Description("Test Description: Validate if user signs in and signs out from Embold using Bitbucket credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Sign in and sign out from Embold.")
    public void EmboldSignOutValidationForBitbucketAccount() {
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
        //Add proper assertion
        String currentTitle = driver.getTitle();
        String expectedTitle = "Embold | Next Level Static Code Analysis";
        Assert.assertEquals(currentTitle, expectedTitle);
    }
}
