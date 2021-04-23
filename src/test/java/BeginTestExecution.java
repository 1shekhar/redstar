import core.Elemental;
import org.testng.annotations.BeforeSuite;

public class BeginTestExecution extends Elemental {

    @BeforeSuite
    public void StartExecution() {
        getBrowserName(getPropertyValue("browser"));
        setWebDriver();
        OpenPlatform(getPropertyValue("appUrl"));
    }
}
