import CustomTestListener.TestReportListener;
import core.Elemental;
import core.DataParser;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EmboldSignInPage;
import pages.EmboldSignUpPage;
import java.io.IOException;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Embold Sign up")
public class EmboldSignUpPageTests extends Elemental {

    static WebElement element;
    EmboldSignInPage signInPage;
    EmboldSignUpPage emboldSignUpPage;

    EmboldSignUpPageTests() {
        super();
        signInPage = new EmboldSignInPage();
        emboldSignUpPage = new EmboldSignUpPage();
        try {
            locatorParser = new DataParser("./src/main/resources/props/Locators.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Story("Sign up page Web element validations")
    @Test(description = "Validate page title - Sign up page.")
    @Description("Test Description: Page title for Sign up page should be 'Embold -Sign up")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if page title shown is correct for Embold Sign up page.")
    public void OpenEmboldSignUpPage()
    {
        signInPage.DisplaySignUpLink().click();
    }

    @Story("Sign up page Web element validations")
    @Test(priority = 1, description = "Validate if login illustration is displayed.")
    @Description("Test Description: Login illustration should be displayed within Sign up window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if login illustration is displayed within Sign up window.")
    public void LoginIllustrationIsDisplayed() {
        element = emboldSignUpPage.DisplayLoginIllustration();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign up page Web element validations")
    @Test(priority = 2, description = "Validate if Embold logo is displayed.")
    @Description("Test Description: Embold logo is displayed on the top left corner of the page.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Embold logo is displayed on top left corner of the page.")
    public void EmboldLogoIsDisplayed() {
        element = emboldSignUpPage.DisplayEmboldLogo();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign up page Web element validations")
    @Test(priority = 3, description = "Validate if Sign up Label is displayed.")
    @Description("Test Description: Sign In Label should be displayed above login window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Sign up label is displayed above Sign up window.")
    public void SignInLabelIsDisplayed() {
        element = emboldSignUpPage.DisplaySignUpLabel();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign up page Web element validations")
    @Test(priority = 4, description = "Validate if Sign up with GitHub button is displayed.")
    @Description("Test Description: Sign up with GitHub button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign up with GitHub button is displayed in Sign up window.")
    public void SignInWithGitHubButtonIsDisplayed() {
        element = emboldSignUpPage.DisplaySignUpWithGitHubButton();
        Assert.assertTrue(element.isDisplayed());
    }

    @Story("Sign up page Web element validations")
    @Test(priority = 5, description = "Validate if Sign up with Bitbucket button is displayed.")
    @Description("Test Description: Sign up with Bitbucket button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign up with Bitbucket button is displayed in Sign up window.")
    public void SignInWithBitbucketButtonIsDisplayed() {
        element = emboldSignUpPage.DisplaySignUpWithBitbucketButton();
        Assert.assertTrue(element.isDisplayed());
    }
    @Story("Sign up page Web element validations")
    @Test(priority = 6, description = "Validate if Sign up link is displayed.")
    @Description("Test Description: Sign In link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In link is displayed below Sign up window.")
    public void SignUpLinkIsDisplayed() {
        element = emboldSignUpPage.DisplaySignInLink();
        Assert.assertTrue(element.isDisplayed());
    }
}
