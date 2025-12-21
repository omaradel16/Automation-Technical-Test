package tests;

import base.MobileUIBaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class TestWeatherApp extends MobileUIBaseTest {

    @BeforeMethod
    void beforeTestExample(Method method){
        // Create a new ExtentTest for each test method, making it thread-safe
        setExtentTest(extent.createTest(method.getName()));
//        mobileObject.terminateApp("com.info.weather.forecast");
        mobileObject.executeScript("mobile:shell", Map.of(
        "command", "pm clear com.info.weather.forecast"
        ));
        mobileObject.activateApp("com.info.weather.forecast");
    }

    @Test
    public void test(){

        ExtentTest test = getExtentTest();
        test.info("");

        settingsScreen.changeTempUnitToFahrenheit();
        settingsScreen.changeTimeFormatTo12Hour();
        settingsScreen.saveSettings();

    }
}
