package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class BaseTest {

    public ExtentReports extent;
    //public ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver.set(driver);
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return javascriptExecutor.get();
    }

    public static void setJavascriptExecutor(JavascriptExecutor javascriptExecutor) {
        BaseTest.javascriptExecutor.set(javascriptExecutor);
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait.get();
    }

    public static void setWebDriverWait(WebDriverWait webDriverWait) {
        BaseTest.webDriverWait.set(webDriverWait);
    }

    public static ExtentTest getExtentTest(){
        return extentTest.get() ;
    }

    public static void setExtentTest(ExtentTest extentTest) {
        BaseTest.extentTest.set(extentTest);
    }


    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<JavascriptExecutor>  javascriptExecutor = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> webDriverWait = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest>    extentTest    = new ThreadLocal<>();

    public static  void setUp(String url) {
        // Automatically manage chromedriver version matching the browser
        WebDriverManager.chromedriver().setup();

        setDriver( new ChromeDriver(new ChromeOptions()));

        setJavascriptExecutor((JavascriptExecutor) getDriver());
        setWebDriverWait( new WebDriverWait(getDriver(), Duration.ofSeconds(30)));
        getDriver().get(url);
    }


    /**
     * To Close and Remove local Thread
     */
    public static synchronized void tearDown() {
        //
        if (getDriver()!= null) {
            getDriver().quit();
            driver.remove();
        }
    }

    @BeforeTest
    public void setUpExtentReport() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-report/amazon_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("UI Test Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Omar Adel");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    @BeforeMethod
    public void init(Method method){
        // Create a new ExtentTest for each test method, making it thread-safe
         setExtentTest(extent.createTest(method.getName()));
        setUp("https://www.amazon.eg/");
        getDriver().manage().window().maximize();

    }

    @AfterMethod
    public void captureResult(ITestResult result) {
        ExtentTest test = getExtentTest(); // Get the current thread's ExtentTest

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("❌ Test Failed: " + result.getName());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("⚠️ Test Skipped: " + result.getName());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot(result.getName(), test);
        }
//        tearDown();
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }

    public void saveScreenshot(String testName, ExtentTest test) {
        String base64Screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        test.info("📸 Screenshot for: " + testName,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

}
