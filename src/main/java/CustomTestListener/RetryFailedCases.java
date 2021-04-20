package CustomTestListener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedCases implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 3;

    @Override
    public boolean retry(ITestResult tResult) {
        if (!tResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                tResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                tResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            tResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
