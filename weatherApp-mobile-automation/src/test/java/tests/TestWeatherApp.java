package tests;

import base.MobileUIBaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class TestWeatherApp extends MobileUIBaseTest {

    @BeforeMethod
    void beforeTestExample(Method method){
        // Create a new ExtentTest for each test method, making it thread-safe
        setExtentTest(extent.createTest(method.getName()));
        mobileObject.terminateApp("com.info.weather.forecast");
//        mobileObject.executeScript("mobile:shell", Map.of(
//        "command", "pm clear com.info.weather.forecast"
//        ));
        mobileObject.activateApp("com.info.weather.forecast");
    }

    @Test
    public void changeTemperatureUnit(){

        ExtentTest test = getExtentTest();
        test.info("<b>Test Scenario:</b> Change Temperature Unit to Fahrenheit");
        test.info(""" 
                  <pre>
                  Given the user is on the Settings screen.
                  <b>When</b> the user changes the temperature unit to Fahrenheit,
                  And saves the settings.
                  <b>Then</b> the temperature unit should be updated to Fahrenheit on the Home screen.
                  </pre>
                  """);

        settingsScreen.changeTempUnitToFahrenheit();
        settingsScreen.saveSettings();
        homeScreen.verifyCurrentTempUnit();

    }

    @Test
    public void changeTimeFormat(){

        ExtentTest test = getExtentTest();
        test.info("<b>Test Scenario:</b> Change Time Format to 12-Hour");
        test.info(""" 
                  <pre>
                  <b>Given</b> the user is on the Settings screen.
                  <b>When</b> the user changes the time format to 12-hour,
                  And saves the settings.
                  <b>Then</b> the time format should be updated to 12-hour on the Home screen.
                  </pre>
                  """);

        settingsScreen.changeTimeFormatTo12Hour();
        settingsScreen.saveSettings();
        homeScreen.verifyCurrentTimeFormat();

    }

    @Test
    public void testHourlyForecastIcons(){

        ExtentTest test = getExtentTest();
        test.info("<b>Test Scenario:</b> Verify Hourly Chart Icons Display");
        test.info(""" 
                  <pre>
                  <b>Given</b> the user is on the Settings screen.
                  <b>When</b> the user saves the settings without making any changes.
                  <b>Then</b> the hourly forecast chart on the Home screen should display
                  the rain chance and humidity icons for the first six hours.
                  </pre>
                  """);

        settingsScreen.saveSettings();
        homeScreen.verifyHourlyChartIconsIsDisplayed();

    }
}
