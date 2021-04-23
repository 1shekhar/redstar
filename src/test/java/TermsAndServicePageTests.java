import CustomTestListener.TestReportListener;
import core.Elemental;
import core.DataParser;
import core.LoadEnvProps;
import io.qameta.allure.*;
import org.openqa.selenium.devtools.v85.network.Network;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.EmboldSignInPage;
import pages.GitHubSignInPage;
import pages.TermsAndServicePage;
import java.io.IOException;


@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Terms and Services")
public class TermsAndServicePageTests extends Elemental {

    TermsAndServicePage servicePage;
    EmboldSignInPage signInPage;
    GitHubSignInPage gitHubSignInPage;

    TermsAndServicePageTests() {
        super();
        try {
            locatorParser =new DataParser("./src/main/resources/props/Locators.properties");
            loadEnvProps=new LoadEnvProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
        servicePage=new TermsAndServicePage();
        signInPage = new EmboldSignInPage();
        gitHubSignInPage=new GitHubSignInPage();
    }

    public void OpenTermsAndServicePage()
    {
        /*Verify the user if exists in DB later and then proceed.
        Check for both GitHub and Bitbucket sign ups, with new login
         */

        signInPage.DisplaySignInWithGitHubButton().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gitHubSignInPage.ProvideGitHubCredentials(getPropertyValue("ghUsername_TSPage"),
                getPropertyValue("ghPassword_TSPage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate consent page.")
    @Description("Test Description: Validate consent page")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate new user has been made to agree to Embold's Terms and Services before accessing Embold.")
    public void ValidateConsentPageURL()
    {
        OpenTermsAndServicePage();
        Assert.assertEquals(driver.getCurrentUrl(),getPropertyValue("appUrl")+"auth/consent/gh");
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate if Embold logo is displayed.")
    @Description("Test Description: Validate if Embold logo is displayed")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Embold logo is displayed on top left corner of the page.")
    public void ValidateEmboldIsDisplayed()
    {
        Assert.assertTrue(servicePage.DisplayEmboldLogo().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate if Terms and Service illustration is displayed.")
    @Description("Test Description: Validate if Terms and Service illustration is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service illustration is displayed.")
    public void ValidateTermsAndServiceIllustration()
    {
        Assert.assertTrue(servicePage.DisplayServiceIllustration().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate if Terms and Service label is displayed.")
    @Description("Test Description: Validate if Terms and Service label is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service label is displayed.")
    public void ValidateTermsAndServiceLabel()
    {
        Assert.assertTrue(servicePage.DisplayTermsAndServiceLabel().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate if Terms and Service Description is displayed.")
    @Description("Test Description: Validate if Terms and Service Description is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service Description is displayed.")
    public void ValidateTermsAndServiceDescription()
    {
        Assert.assertTrue(servicePage.DisplayTermsAndServiceDescription().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate Terms and Service Consent checkbox.")
    @Description("Test Description: Validate if Terms and Service Consent checkbox is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service Consent checkbox is displayed.")
    public void ValidateTermsAndServiceConsentCheckbox()
    {
        Assert.assertTrue(servicePage.DisplayConsentCheckbox().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate Terms and Service Consent description.")
    @Description("Test Description: Validate if Terms and Service Consent description is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service Consent description is displayed.")
    public void ValidateTermsAndServiceConsentDescription()
    {
        FluentWaitForWebElement("terms_Service_AgreeText");
        String agree=servicePage.DisplayConsentText().getText();
        Assert.assertEquals(agree,"I agree with Embold's");
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate Terms and Service Consent link page.")
    @Description("Test Description: Validate if Terms and Service Consent link is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service Consent page link is displayed.")
    public void ValidateTermsAndServiceConsentLink()
    {
        Assert.assertTrue(servicePage.DisplayConsentLink().isDisplayed());
    }

    @Story("Terms and Service page validations")
    @Test(description = "Validate Terms and Service submit button.")
    @Description("Test Description: Validate if Terms and Service submit button is displayed.")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if Terms and Service submit is displayed.")
    public void ValidateTermsAndServiceSubmitButton()
    {
        Assert.assertTrue(servicePage.DisplaySubmitButton().isDisplayed());
    }

}
