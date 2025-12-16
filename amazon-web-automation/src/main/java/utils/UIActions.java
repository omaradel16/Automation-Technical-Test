package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static utils.BaseTest.getDriver;
import static utils.BaseTest.getWebDriverWait;

public class UIActions {

    public void clearAndText (By element, String text ){

        WebElement firstNameField = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        firstNameField.clear();
        firstNameField.sendKeys(text);

    }

    public void clickOnElement (By locator ) {
        WebElement firstNameField2 = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        firstNameField2.click();
    }

    public boolean isElementPresent(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns the first visible element matching the given locator.
     *
     * @param locator The By locator to find the element.
     * @return The first visible WebElement, or null if no visible elements are found.
     *
     * @throws AssertionError If no visible elements are found.
     */
    public WebElement getFirstPresentElement(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        int i = 1;
        System.out.println("num of elements found for locator: " + locator + " is: " + elements.size());
        for (WebElement element : elements) {
            try {
                getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
                if (element.isDisplayed()) {  // Check if the element is visible
                    System.out.println("got the element: " + i);
                    return element;  // Return the first visible element
                }
            } catch (Exception e) {
                System.out.println("Failed to click element" + i);
            }
            i+=1;
        }

        Assert.fail("All attempts to get the element failed!");
        return null;  // Return null if no visible elements are found
    }

    /**
     * Clicks an element using JavaScript.
     * Includes a 2-second delay after the click.
     *
     * @param locator The By locator for the element to click.
     * @throws RuntimeException If the thread sleep is interrupted.
     */
    public void clickWithJS(By locator){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        // Wait for element and scroll into view
        WebElement element = getWebDriverWait().until(driver -> {
            WebElement element1 = driver.findElement(locator);
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                    element1
            );
            return element1;
        });
        try{
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("Failed to click " + element);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
