package screens;

import actions.MobileUIActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

import java.time.Duration;
import java.util.Collections;

public class HomeScreen extends BaseScreen {
    public HomeScreen(AppiumDriver<?> appium) {
        super(appium);
    }

    // Locators ***************************************************************************************************

    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "currentTemperatureUnit")
    public WebElement currentTemperatureUnit;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "currentDateTime")
    public WebElement currentDateTime;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Home_Screen, nameOfTheLocator = "chartHourly")
    public WebElement chartHourly;

    String hourlyItemXpath = "(//android.widget.LinearLayout[@resource-id='com.info.weather.forecast:id/ll_hour_root_container'])[%d]";
    String rainChanceIcon = "//android.widget.ImageView[@resource-id='com.info.weather.forecast:id/iv_rain_chance']";
    String humidityIcon = "//android.widget.LinearLayout[@resource-id='com.info.weather.forecast:id/ll_humidity']/android.widget.ImageView";

    // Assertions  ***************************************************************************************************

    public void verifyCurrentTempUnit(){

        MobileUIActions.setExplicitWait(mobileObject, currentTemperatureUnit, 20);
        String tempUnit= MobileUIActions.getText(currentTemperatureUnit);

        System.out.println("Verifying that the temperature unit is set to Fahrenheit...");

        Assert.assertTrue(tempUnit.contains("F"), "The temperature unit is NOT set to Fahrenheit." +
                " Current temperature unit: " + tempUnit);

    }

    public void verifyCurrentTimeFormat(){

        MobileUIActions.setExplicitWait(mobileObject, currentDateTime, 20);
        String dateTime= MobileUIActions.getText(currentDateTime);

        System.out.println("Verifying that the time format is set to 12-hour...");

        Assert.assertTrue(dateTime.contains("AM") || dateTime.contains("PM"), "The time format is NOT set to 12-hour." +
                " Current date and time: " + dateTime);

    }

    public void verifyHourlyChartIconsIsDisplayed(){

        MobileUIActions.setExplicitWait(mobileObject, chartHourly, 20);

        for (int i = 1; i < 7; i++) {
            System.out.println("Validating Hour " + i + "...");

            // Construct the XPath for the specific hour container (e.g., item[1], item[2]...)
            String currentItemXpath = String.format(hourlyItemXpath, i);

            // Scroll to the current hour item
            if (i > 4){
                MobileUIActions.scrollHorizontally(mobileObject, chartHourly);
            }

            try {
                WebElement rain = mobileObject.findElement(By.xpath(currentItemXpath + rainChanceIcon));
                WebElement humidity = mobileObject.findElement(By.xpath(currentItemXpath + humidityIcon));

                if (!rain.isDisplayed()) {
                    Assert.fail("Rain chance icon is NOT displayed for hour " + i);
                }
                if (!humidity.isDisplayed()) {
                    Assert.fail("Humidity icon is NOT displayed for hour " + i);
                }

                System.out.println("Rain chance and Humidity icons are displayed for hour " + i);
            } catch (Exception e) {
                Assert.fail("Failed to locate icons for hour " + i + ": " + e.getMessage());
            }

        }
    }

}
