package CustomTestListener;

import core.Elemental;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestReportListener extends Elemental implements ITestListener
{
    private static String getMethodNameUnderExecution(ITestResult iTestResult)
    {
        return iTestResult.getTestClass().getName()+"."+iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment (value = "Screenshot", type="image/png")
    public byte[] saveScreenshotAsPNG(WebDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment (value="{0}", type="text/plain")
    public static String storeTextExecutionLogs(String logs)
    {
        return logs;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {


        System.out.println("Validating:::  "+ getMethodNameUnderExecution(iTestResult)+" ");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        if (driver instanceof WebDriver) {
            System.out.println("Validation for :"
                    + getMethodNameUnderExecution(iTestResult)+" has failed. Refer attached screenshot!");
            saveScreenshotAsPNG(driver);
        }
        storeTextExecutionLogs("Validation for "
                +getMethodNameUnderExecution(iTestResult) + " has failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
