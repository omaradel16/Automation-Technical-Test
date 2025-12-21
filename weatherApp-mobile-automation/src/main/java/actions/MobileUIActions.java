package actions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobileUIActions {

    public static void scrollHorizontally(AppiumDriver mobileObject, WebElement element) {

        int startX = element.getLocation().getX() + (element.getSize().getWidth() * 3 / 4);
        int endX = element.getLocation().getX() + (element.getSize().getWidth() / 4);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        mobileObject.perform(Collections.singletonList(swipe));


    }

    public static void setExplicitWait(AppiumDriver mobileObject,WebElement element, int timeoutInSeconds) {

        (new WebDriverWait(mobileObject, timeoutInSeconds)).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.or(

                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.elementToBeClickable(element)));
    }

    public static void enterText(WebElement element, String text) {


        System.out.println("start ");
        System.out.println("Text: "+ text);
        element.clear();
        element.sendKeys(text);
        System.out.println("End sendKeys ");

    }

    public static String getText(WebElement element) {
        System.out.println("start ");
        return element.getText();
    }

    public static void Click(WebElement element) {

        long TimeMillis = System.currentTimeMillis();
        long startTime = TimeUnit.MILLISECONDS.toSeconds(TimeMillis);
        System.out.println("start time to Click Action "+startTime);

        // Get element bounds
//        Rectangle rect = element.getRect();
//        int centerX = rect.getX() + (rect.getWidth() / 2);
//        int centerY = rect.getY() + (rect.getHeight() / 2);
//
//        // Print the coordinates
//        System.out.println("Clicking at coordinates: X=" + centerX + ", Y=" + centerY);

        element.click();
        TimeMillis = System.currentTimeMillis();
        long EndTime = TimeUnit.MILLISECONDS.toSeconds(TimeMillis);
        System.out.println("End time to Click Action "+EndTime);
        System.out.println("Difference between Start and EndTime " +(EndTime - startTime));

    }

}
