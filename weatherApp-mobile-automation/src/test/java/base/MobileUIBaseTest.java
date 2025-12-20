package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import screens.HomeScreen;
import screens.SettingsScreen;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static base.MobileCompatibilityConfig.androidConfig;
import org.openqa.selenium.html5.Location;

public class MobileUIBaseTest {

    protected static AppiumDriver mobileObject;
    protected HomeScreen homeScreen;
    protected SettingsScreen settingsScreen;

    public ExtentReports extent;

    public static ExtentTest getExtentTest(){
        return extentTest.get() ;
    }

    public static void setExtentTest(ExtentTest extentTest) {
        MobileUIBaseTest.extentTest.set(extentTest);
    }

    private static ThreadLocal<ExtentTest>    extentTest    = new ThreadLocal<>();

    @BeforeTest
    public void setUpExtentReport() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/WeatherApp_" + timeStamp;

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("UI Test Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Omar Adel");
        extent.setSystemInfo("OS", "Android 11.0");
    }

    @BeforeSuite
    @Parameters({"PlatformType", "installWay", "buildPath"})
    public void BeforeSuite(@Optional("Android") String PlatformType, @Optional("installFromResources")String installWay,
                            @Optional("xxx")String buildPath) throws MalformedURLException {
        mobileObject = androidConfig();

    }

    @BeforeClass
    @Parameters({"PlatformType"})
    public void BeforeClass(@Optional("Android")String PlatformType) throws MalformedURLException {

        homeScreen          = new HomeScreen(mobileObject);
        settingsScreen         = new SettingsScreen(mobileObject);

        setDeviceLocation();

    }


    @AfterClass
    public void AfterClass() {

//        mobileObject.quit();
    }

    @AfterSuite
    public void AfterSuite() {
        //    mobileObject.removeApp(getDesiredCapability("appPackage"));
//        stopAppiumServer();
    }


    @AfterMethod
    public void captureResult(ITestResult result) {
        ExtentTest test = getExtentTest(); // Get the current thread's ExtentTest

        // save video
//        saveVideo(mobileObject, result.getName());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("❌ Test Failed: " + result.getName());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("⚠️ Test Skipped: " + result.getName());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Taking Screenshot....");
//            Helper.captureScreenshot(mobileObject, result.getName());
            saveScreenshot(mobileObject, result.getName(), test);

        }
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }

    public void saveScreenshot(WebDriver browserObject, String testName, ExtentTest test) {
        String base64Screenshot = ((TakesScreenshot) browserObject).getScreenshotAs(OutputType.BASE64);
        test.info("📸 Screenshot for: " + testName,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    public void setDeviceLocation() {

        Location location = new Location(30.0444, 31.2357, 10);

        mobileObject.setLocation(location);
        System.out.println("Device location set to: Cairo, Egypt");
    }
}
