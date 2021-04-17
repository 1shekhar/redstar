import core.Elemental;
import org.testng.annotations.AfterSuite;

public class EndTestExecution extends Elemental {
    @AfterSuite
    public void EndExecution() {
        tearDown();
    }
}
