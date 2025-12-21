package screens;

import actions.MobileUIActions;
import com.github.hemanthsridhar.support.SearchBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import screenFactory.BaseScreen;
import screenFactory.ScreenObjectsConfig;

public class SettingsScreen extends BaseScreen {
    public SettingsScreen(AppiumDriver<?> appium) {
        super(appium);
    }

    // Locators ***************************************************************************************************

    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "tempDropdown")
    public WebElement tempDropdown;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "FahrenheitTxt")
    public WebElement FahrenheitTxt;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "timeFormatDropdown")
    public WebElement timeFormatDropdown;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "twelveHourTxt")
    public WebElement twelveHourTxt;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "doneBtn")
    public WebElement doneBtn;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "gotItBtn")
    public WebElement gotItBtn;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "cancelBtn")
    public WebElement cancelBtn;
    @SearchBy(locatorsFile = ScreenObjectsConfig.Settings_Screen, nameOfTheLocator = "allowWhileUsingAppBtn")
    public WebElement allowWhileUsingAppBtn;

    // Methods  ***************************************************************************************************

    public void changeTempUnitToFahrenheit(){

        System.out.println("Changing temperature unit to Fahrenheit...");

        MobileUIActions.setExplicitWait(mobileObject, tempDropdown, 20);
        MobileUIActions.Click(tempDropdown);

        MobileUIActions.setExplicitWait(mobileObject, FahrenheitTxt, 15);
        MobileUIActions.Click(FahrenheitTxt);
    }

    public void changeTimeFormatTo12Hour(){

        System.out.println("Changing time format to 12-hour...");

        MobileUIActions.setExplicitWait(mobileObject, timeFormatDropdown, 20);
        MobileUIActions.Click(timeFormatDropdown);

        MobileUIActions.setExplicitWait(mobileObject, twelveHourTxt, 15);
        MobileUIActions.Click(twelveHourTxt);
    }

    public void saveSettings(){

        System.out.println("Saving settings...");

        MobileUIActions.setExplicitWait(mobileObject, doneBtn, 15);
        MobileUIActions.Click(doneBtn);

        try {
            MobileUIActions.setExplicitWait(mobileObject, gotItBtn, 5);
            MobileUIActions.Click(gotItBtn);
        } catch (Exception e) {
            //
        }

        try {
            MobileUIActions.setExplicitWait(mobileObject, allowWhileUsingAppBtn, 5);
            MobileUIActions.Click(allowWhileUsingAppBtn);
        } catch (Exception e) {
            //
        }

        try {
            MobileUIActions.setExplicitWait(mobileObject, cancelBtn, 10);
            MobileUIActions.Click(cancelBtn);
        } catch (Exception e) {
            //
        }
    }

}
