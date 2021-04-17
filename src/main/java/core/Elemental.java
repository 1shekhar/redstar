package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class  Elemental {

    public static WebDriver driver;
    public static String browserName;
    public static LocatorParser locatorParser;
    public static Capabilities caps;


    //Use maven dependency to create driver
    public void setWebDriver() {
        String browser = getBrowserName(browserName);
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/executables/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            //WebDriverManager.chromedriver().setup();
            options.addArguments("start-maximized");
            options.addArguments("--disable-gpu");
            options.addArguments("enable-automation");
            //options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.addArguments("--test-type");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./src/main/resources/executables/geckodriver.exe");
            //WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "./src/main/resources/executables/msedgedriver.exe");
            //WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
    }

    public void OpenPlatform() {
        driver.get(locatorParser.getSingularProperty("url"));
        waitTillWholePageIsLoaded();
    }

    public void waitTillWholePageIsLoaded()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public String getBrowserName(String browser) {
        browserName = browser;
        return browserName;
    }

    public void WaitTillElementIsClickable(String elementLocator) {
        By element = locatorParser.getElementLocator(elementLocator);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }


    public void WaitTillPresenceOfElementIsLocated(String elementLocator) {
        By element = locatorParser.getElementLocator(elementLocator);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void tearDown() {
        try {
            caps = ((RemoteWebDriver) driver).getCapabilities();
            CreateEnvironmentProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    public void CreateEnvironmentProperties() throws IOException {
        File envProps = new File(".//allure-results//environment.properties");
        if(envProps.exists() && !envProps.isDirectory())
        {
            envProps.createNewFile();
        }
        FileOutputStream propFile = new FileOutputStream(envProps, false);
            try
            {
                Properties prop = new Properties();
                prop.setProperty("Embold URL",locatorParser.getSingularProperty("url"));
                prop.setProperty("Platform", System.getProperty("os.name"));
                prop.setProperty("BrowserName", String.valueOf(caps.getCapability("browserName")));
                prop.setProperty("BrowserVersion", String.valueOf(caps.getCapability("browserVersion")));
                prop.setProperty("ScreenResolution",String.valueOf(driver.manage().window().getSize()));
                prop.setProperty("ExecutedBy",System.getProperty("user.name"));
                prop.store(propFile, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
}