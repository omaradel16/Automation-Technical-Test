package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.UIActions;

import java.util.List;

import static utils.BaseTest.getDriver;
import static utils.BaseTest.getWebDriverWait;

public class CartPage {

    // Locators -------------------------------------------------------------------------------------------------------

    private static final By elemProceedToBuyBtn = By.xpath("//input[@name='proceedToRetailCheckout']");
    private static final By elemRadioBtn = By.xpath("//input[@type='radio' and contains(@value,'paymentMethod=Loan')]");
    private static final By elemUsePaymentBtn = By.xpath("//span[normalize-space()='Use this payment method']//input[@type='submit']");
    private static final By elemSummaryBox = By.id("subtotals-marketplace-table");
    private static final By elemSubtotalItemsTxt = By.xpath("//input[@id='subtotal-line-type' and @value='ITEMS_TAX_INCLUSIVE']/following-sibling::span");
    private static final By elemShippingTxt = By.xpath("//input[@id='subtotal-line-type' and @value='SHIPPING_TAX_INCLUSIVE']/following-sibling::span");
    private static final By elemTotalTxt = By.xpath("//input[@id='subtotal-line-type' and @value='TOTAL_BEFORE_SPECIAL_PAYMENTS_TAX_INCLUSIVE']/following-sibling::span");


    // Methods -------------------------------------------------------------------------------------------------------

    public void checkout() {

        UIActions uiActions = new UIActions();

        // 1. Proceed to checkout
        System.out.println("Navigating to Checkout...");
        uiActions.clickOnElement(elemProceedToBuyBtn);

        // 2. Handle Address and Payment method
        System.out.println("Selecting Payment Method...");

        // Locate the COD radio button. [Not allowed for orders more than 5K]
        // Locate Pay Later with Valu
//        WebElement codRadioButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemRadioBtn));
        uiActions.clickOnElement(elemRadioBtn);

        // 3. Click "Use this payment method"
        uiActions.clickOnElement(elemUsePaymentBtn);

        // 4. Validate Order Totals
//        verifyTotalCalculation();

    }


    private double parsePrice(String priceText) {
        // Remove "EGP", commas, and spaces. Keep digits and the decimal point.
        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
        if (cleanPrice.isEmpty()) return 0.0;
        return Double.parseDouble(cleanPrice);
    }

    // Assertions -----------------------------------------------------------------------------------------------------

    public void verifyTotalCalculation(){

        System.out.println("Validating Order Totals...");

        // Wait for the Order Summary box to be visible
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(elemSummaryBox));

        // Get Item Subtotal
        String subtotalText = getDriver().findElement(elemSubtotalItemsTxt).getText();
        double subtotal = parsePrice(subtotalText);

        // Get Shipping Fee
        String shippingText = getDriver().findElement(elemShippingTxt).getText();

        double shipping = 0.0;
        if (!shippingText.toLowerCase().contains("free") && !shippingText.matches(".*--.*")) {
            shipping = parsePrice(shippingText);
        }

        // Get Total
        String totalElement = getDriver().findElement(elemTotalTxt).getText();
        double actualTotal = parsePrice(totalElement);

        // 4. Calculate Expected Total
        double expectedTotal = subtotal + shipping;

        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping: " + shipping);
        System.out.println("Expected: " + expectedTotal);
        System.out.println("Actual:   " + actualTotal);

        // Use a small delta (0.01) for double comparison to avoid floating point errors
        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Order Total calculation is incorrect!");

        System.out.println("Validation Successful: Order Total is correct.");
    }
}
