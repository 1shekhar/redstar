import core.Elemental;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.RepositoryListPage;

import java.io.IOException;

public class EmboldSignOutTests extends Elemental {
    RepositoryListPage repositoryListPage = new RepositoryListPage();
    WebElement element;

    EmboldSignOutTests() throws IOException {
        super();
    }


    public void StartExecution() {
        setWebDriver();
    }

    public void UserAvatarInHeaderIsDisplayed() {
        element = repositoryListPage.DisplayUserAvatarInHeader();
        Assert.assertTrue(element.isDisplayed(), "User avatar in header is displayed.");
    }

    public void SignOutButtonIsDisplayed() {
        repositoryListPage.DisplayUserAvatarInHeader().click();
        element = repositoryListPage.DisplaySignOutButton();
        Assert.assertTrue(element.isDisplayed(), "Sign out panel is opened.");
    }

}
