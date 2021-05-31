package signin;

import core.Elemental;
import io.qameta.allure.*;
import listeners.TestReportListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.signin.EmboldSignInPage;
import utilities.APIInvoker;

@Listeners({TestReportListener.class})
@Epic("Regression Tests")
@Feature("Sign In Page")
public class EmboldSignInPageTests extends Elemental {

    private static final Logger logger = LogManager.getLogger(EmboldSignInPageTests.class);
    EmboldSignInPage signInPage;
    APIInvoker apiInvoker;

    EmboldSignInPageTests() {
        super();
        signInPage = new EmboldSignInPage();
    }

    @Issues({@Issue("emboldSignInPageTitleIsDisplayed")})
    @Story("Sign In page Web element validations")
    @Test(priority = 1, description = "Validate page title - Sign In page.")
    @Description("Test Description: Page title for Sign In page should be 'Embold | Next Level Static Code Analysis'")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Validate if page title shown is correct for Embold Sign In page.")
    public void testEmboldSignInPageTitleIsDisplayed() {
        Assert.assertEquals(driver.getTitle(), "Embold | Next Level Static Code Analysis");
        logger.info("Sign in page title displayed is correct.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if login illustration is displayed.", dependsOnMethods = {"testEmboldSignInPageTitleIsDisplayed"})
    @Description("Test Description: Login illustration should be displayed within Sign In window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if login illustration is displayed in Sign In window.")
    public void testLoginIllustrationIsDisplayed() {
        Assert.assertTrue(signInPage.displayLoginIllustration().isDisplayed());
        logger.info("Login illustration displayed.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if Embold logo is displayed.")
    @Description("Test Description: Embold logo is displayed on the top left corner of the page.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Embold logo is displayed on top left corner of the page.")
    public void testEmboldLogoIsDisplayed() {
        Assert.assertTrue(signInPage.displayEmboldLogo().isDisplayed());
        logger.info("Embold logo is displayed.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if Sign In Label is displayed.")
    @Description("Test Description: Sign In Label should be displayed above login window.")
    @Severity(SeverityLevel.MINOR)
    @Step("Check if Sign In label is displayed above Sign In window.")
    public void testSignInLabelIsDisplayed() {
        apiInvoker = new APIInvoker();
        Assert.assertTrue(signInPage.displaySignInLabel().isDisplayed());
        logger.info("Sign in label is displayed.");
        String translationLabel = apiInvoker.getSignInPageLabelsFromTranslationJSON("sign_in");
        Assert.assertEquals(signInPage.displaySignInLabel().getText(), translationLabel);
        logger.info("Sign in label is correct.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if Sign In with GitHub button is displayed.")
    @Description("Test Description: Sign In with GitHub button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with GitHub button is displayed in Sign In window.")
    public void testSignInWithGitHubButtonIsDisplayed() {
        Assert.assertTrue(signInPage.displaySignInWithGitHubButton().isDisplayed());
        logger.info("Sign with Github button is displayed.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if Sign In with Bitbucket button is displayed.")
    @Description("Test Description: Sign In with Bitbucket button should be displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign In with Bitbucket button is displayed in Sign In window.")
    public void testSignInWithBitbucketButtonIsDisplayed() {
        Assert.assertTrue(signInPage.displaySignInWithBitbucketButton().isDisplayed());
        logger.info("Sign in Bitbucket button is displayed.");
    }

    @Story("Sign In page Web element validations")
    @Test(description = "Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Check if Sign up link is displayed below Sign In window.")
    public void testSignUpLinkIsDisplayed() {
        Assert.assertTrue(signInPage.displaySignUpLink().isDisplayed());
        logger.info("Sign up link is displayed.");
    }

    @Issues({@Issue("Intentionally_Failed_emboldSignInPageTitleIsDisplayed")})
    @Story("Intentionally_Failed_Sign In page Web element validations")
    @Test(priority = 1, description = "Intentionally_Failed_Validate page title - Sign In page.")
    @Description("Test Description: Page title for Sign In page should be 'Embold | Next Level Static Code Analysis'")
    @Severity(SeverityLevel.TRIVIAL)
    @Step("Intentionally_Failed_Validate if page title shown is correct for Embold Sign In page.")
    public void Intentionally_Failed_testEmboldSignInPageTitleIsDisplayed() {
        Assert.assertEquals(driver.getTitle(), "Embold | Next Level Static Code Analysiss");
        logger.error("Intentionally_Failed_Sign in page title displayed is correct.");
    }

    @Issues({@Issue("Intentionally_Failed_emboldSignInPageTitleIsDisplayed")})
    @Story("Intentionally_Failed_Sign In page Web element validations")
    @Test(description = "Intentionally_Failed_Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Intentionally_Failed_Check if Sign up link is displayed below Sign In window.")
    public void Intentionally_Failed_testSignUpLinkIsDisplayed() {
        Assert.assertFalse(signInPage.displaySignUpLink().isDisplayed());
        logger.error("Intentionally_Failed_Sign up link is displayed.");
    }

    @Issues({@Issue("Intentionally_Skipped_emboldSignInPageTitleIsDisplayed")})
    @Story("Intentionally_Skipped_Sign In page Web element validations")
    @Test(description = "Intentionally_Skipped_Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Intentionally_Skipped_Check if Sign up link is displayed below Sign In window.")
    public void Intentionally_Skipped_emboldSignInPageTitleIsDisplayed() {
        try {
            Assert.assertFalse(signInPage.displaySignUpLink().isDisplayed());
        } catch (AssertionError e) {
            logger.warn("Intentionally_Skipped_Sign up link is displayed.");
            throw new SkipException("");
        }
    }

    @Issues({@Issue("Intentionally_Broken_emboldSignInPageTitleIsDisplayed")})
    @Story("Intentionally_Broken_Sign In page Web element validations")
    @Test(description = "Intentionally_Broken_Validate if Sign up link is displayed.")
    @Description("Test Description: Sign up link should be displayed below login window.")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Intentionally_Broken_Check if Sign up link is displayed below Sign In window.")
    public void Intentionally_Broken_emboldSignInPageTitleIsDisplayed() {
        Assert.assertTrue(signInPage.displaySignUpLinkIntentionalyBroken().isDisplayed());
        logger.info("Intentionally_Broken_Sign up link is displayed.");
    }
}