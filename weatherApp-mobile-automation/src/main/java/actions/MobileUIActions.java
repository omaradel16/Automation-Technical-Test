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

    /**
     *
     * @param mobileObject
     * @param element
     * @param timeout
     */
    public static void scrollToSpecificElement(AppiumDriver mobileObject, WebElement element, int timeout)     //Normal Case of Scrolling
    {
        //////////////////////////////////
        //////////////////////////////////
        long TimeMillis = System.currentTimeMillis();
        long startTime = TimeUnit.MILLISECONDS.toSeconds(TimeMillis);
        long currentTime = startTime;
        System.out.println("Scroll Down");

        Dimension dim =  mobileObject.manage().window().getSize();
        int H =dim.getHeight();
        int W =dim.getWidth();
        System.out.println("start");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), W/2,(int)(H*0.6)); //0.8 //.4
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(),W/2 , (int)(H*0.5));//0.5 //46
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        System.out.println("End");

        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);


        while(currentTime < startTime + timeout){   //!element.isDisplayed()   //!elementIsDisplayed(element)
            try {
                System.out.println("start While For Scrolling Down");
                setExplicitWait(mobileObject, element, 2);
                if(element.isDisplayed())//element.isDisplayed()
                {
                    System.out.println("Displayed");
                    break;
                }
                TimeMillis = System.currentTimeMillis();
                currentTime = TimeUnit.MILLISECONDS.toSeconds(TimeMillis);
                if(!element.isDisplayed())
                {
                    mobileObject.perform(Arrays.asList(swipe));
                    System.out.println("start Scrolling");
                }
            }
            catch (Exception e) {
                System.out.println("start catch");
                mobileObject.perform(Arrays.asList(swipe));
                System.out.println("start Scrolling");
                TimeMillis = System.currentTimeMillis();
                currentTime = TimeUnit.MILLISECONDS.toSeconds(TimeMillis);
            }

        }

    }

    public static void setExplicitWait(AppiumDriver mobileObject,WebElement element, int timeoutInSeconds) {

        (new WebDriverWait(mobileObject, timeoutInSeconds)).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.or(

                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.elementToBeClickable(element)));
    }

    /**
     * Waits for progressBar to load
     * @param mobileObject
     * @param element progressBar locator
     */
    public static void waitForPageToLoad(AppiumDriver mobileObject,WebElement element){
        System.out.println("Start waiting for page to load");
        (new WebDriverWait(mobileObject, 120)).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOf(element));

        System.out.println("End waiting.");
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

    public static boolean checkElementNonExistence(List<WebElement> element) throws InterruptedException {
        System.out.println("start ");
        Thread.sleep(5000);
        return element.isEmpty();
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

    public static void switchContexttoWebViewContext(AppiumDriver mobileObject,String context)
    {
        System.out.println(mobileObject.getContextHandles().toString());
        mobileObject.execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", context));
    }

    public static void switchtoUrlWindow(AppiumDriver mobileObject) {
        Iterable<String> windowHandles = mobileObject.getWindowHandles();
        for (String windowHandle : windowHandles) {
            System.out.println("Window handle: " + windowHandle);
            System.out.println(mobileObject.getCurrentUrl());
            mobileObject.switchTo().window(windowHandle);

            if (mobileObject.getCurrentUrl().contains("landingBage")) {
                break;
            }
        }
    }

    public static boolean checkIfElementISDisplayed(WebElement element) {

        return element.isDisplayed();

    }

    public static void scrollIntoView(AppiumDriver mobileObject,String txt)
    {
        mobileObject.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
                + ".instance(0)).scrollIntoView(new UiSelector().textMatches(\""+txt+"\").instance(0))"));
    }

    public static void scrollToEnd(AppiumDriver mobileObject, int scrollCount) {
        Dimension dim = mobileObject.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 0; i < scrollCount; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, (int) (height * 0.8)));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), width / 2, (int) (height * 0.3)));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            mobileObject.perform(Collections.singletonList(swipe));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void updateSeekBar(AppiumDriver mobileObject, WebElement element){

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);


        int startX = element.getLocation().getX();
        int endX = startX + element.getSize().getWidth();
        int y = element.getLocation().getY() + element.getSize().getHeight() / 2;

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));


        int moveToX = (int)(startX + (endX - startX) * 0.7);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), moveToX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        mobileObject.perform(Arrays.asList(swipe));

    }

}
