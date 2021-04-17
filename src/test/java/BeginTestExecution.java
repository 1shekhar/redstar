import core.Elemental;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BeginTestExecution extends Elemental {

    @BeforeSuite
    @Parameters({"browser","appURL"})
    public void StartExecution(@Optional String browser, @Optional String appURL){
        getBrowserName(browser);
        setWebDriver();
        OpenPlatform(appURL);
    }
}
