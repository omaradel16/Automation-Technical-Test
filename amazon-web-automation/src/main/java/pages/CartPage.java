package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.UIActions;

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
    private static final By elemPlaceOrder = By.id("placeOrder");

    // Methods -------------------------------------------------------------------------------------------------------
    /**
     * Completes the checkout process up to the payment method selection.
     * <p>
     * Steps:
     * 1. Clicks "Proceed to Buy" from the cart page.
     * 2. Selects a payment method (e.g., Pay Later with Valu).
     * 3. Clicks "Use this payment method" to confirm.
     */
    public void checkout() {

        UIActions uiActions = new UIActions();

        // 1. Proceed to checkout
        System.out.println("Navigating to Checkout...");
        uiActions.clickOnElement(elemProceedToBuyBtn);

        // 2. Handle Address and Payment method
        System.out.println("Selecting Payment Method...");

        // Locate the COD radio button. [Not allowed for orders more than 5K]
        // Locate Pay Later with Valu
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemRadioBtn));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        uiActions.clickOnElement(elemRadioBtn);

        // 3. Click "Use this payment method"
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        uiActions.clickOnElement(elemUsePaymentBtn);
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(elemPlaceOrder));
    }

    /**
     * Utility method to extract a numeric double value from a raw price string.
     * <p>
     * This method removes all non-numeric characters (currency symbols, commas, spaces)
     * except for the decimal point.
     * <p>
     * Example: "EGP 1,250.00" becomes 1250.00.
     *
     * @param priceText The raw string fetched from the UI (e.g., "EGP 1,200").
     * @return The parsed double value, or 0.0 if the cleaned string is empty.
     */
    private double parsePrice(String priceText) {
        // Remove "EGP", commas, and spaces. Keep digits and the decimal point.
        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
        if (cleanPrice.isEmpty()) return 0.0;
        return Double.parseDouble(cleanPrice);
    }

    // Assertions -----------------------------------------------------------------------------------------------------
    /**
     * Validates the final order arithmetic on the Checkout Review page.
     * <p>
     * Validation Logic:
     * 1. Scrapes the <b>Item Subtotal</b> and <b>Shipping Fee</b> from the summary box.
     * 2. Logic handles non-numeric shipping text (e.g., "Free" or "--") by treating it as 0.0.
     * 3. Calculates {@code Expected Total = Subtotal + Shipping}.
     * 4. Compares the Expected Total against the <b>Order Total</b> displayed on the UI.
     * 5. Uses a delta of 0.01 in the assertion to handle floating-point precision.
     *
     * @throws AssertionError if the calculated total differs from the displayed total.
     */
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
