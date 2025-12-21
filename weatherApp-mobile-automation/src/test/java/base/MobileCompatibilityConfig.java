package base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class MobileCompatibilityConfig {

    private static String fileName = "AndroidDesiredCapabilities.properties";
    private static DesiredCapabilities caps = new DesiredCapabilities();

    public static AndroidDriver androidConfig() throws MalformedURLException {
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getDesiredCapability("platformName"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getDesiredCapability("automationName"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getDesiredCapability("platformVersion"));

        caps.setCapability(MobileCapabilityType.DEVICE_NAME, getDesiredCapability("deviceName"));
        caps.setCapability(MobileCapabilityType.UDID, getDesiredCapability("udid"));

        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);

        caps.setCapability("appPackage", getDesiredCapability("appPackage"));
        caps.setCapability("appActivity", getDesiredCapability("appActivity"));
        caps.setCapability("autoGrantPermissions", getDesiredCapability("autoGrantPermissions"));

        caps.setCapability(MobileCapabilityType.APP, Paths.get(System.getProperty("user.dir"), "weather-app.apk").toString());

        return new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    public static String getDesiredCapability(String capsName) {

        String filePath = "configs/data/" + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("file not found");
            e1.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            System.out.println("file not read");
            e.printStackTrace();
        }
        return prop.getProperty(capsName);
    }

}
