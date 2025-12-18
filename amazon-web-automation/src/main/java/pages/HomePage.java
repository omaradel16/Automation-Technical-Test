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

public class HomePage {

    // Locators -------------------------------------------------------------------------------------------------------

    private static final By elemSignIn = By.id("nav-link-accountList");
    private static final By elemHamburgerMenu = By.id("nav-hamburger-menu");
    private static final By elemAllCategories = By.xpath("//a[@aria-label='See All Categories']");
    private static final By elemVideoGamesCategory = By.xpath("//div[@id='hmenu-content']//div[text()='Video Games']");
    private static final By elemAllVideoGames = By.xpath("//a[@class='hmenu-item' and text()='All Video Games']");

    private static final By elemFreeShippingFilter = By.xpath("//span[contains(@class,'a-size-base') and normalize-space()='Free Shipping']");
    private static final By elemConditionNewFilter = By.xpath("//span[text()='New' and contains(@class, 'a-size-base')]");
    private static final By elemDropDown = By.xpath("//span[@class='a-dropdown-prompt']");
    private static final By elemHighToLowOption = By.xpath("//a[contains(text(), 'Price: High to Low')]");

    private static final By elemProducts = By.xpath("//div[@data-component-type='s-search-result']");
    private static final By elemPrice = By.xpath(".//span[@class='a-price-whole']");
    private static final By elemAddToCartBtn = By.xpath(".//button[@name='submit.addToCart']");
    private static final By elemNextBtn = By.xpath("//a[contains(@class, 's-pagination-next')]");
    private static final By elemCartCount = By.id("nav-cart-count");


    // Methods -------------------------------------------------------------------------------------------------------
    public void clickSign() {

        UIActions uiActions = new UIActions();
        uiActions.clickOnElement(elemSignIn);
    }

    public void navigateToAllVideoGames() {

        UIActions uiActions = new UIActions();

        uiActions.clickOnElement(elemHamburgerMenu);


        uiActions.clickWithJS(elemAllCategories);
        uiActions.clickWithJS(elemVideoGamesCategory);
        uiActions.clickWithJS(elemAllVideoGames);
    }

    public void filterResults(){
        UIActions uiActions = new UIActions();

        WebElement freeShippingFilter = getDriver().findElement(elemFreeShippingFilter);
        WebElement conditionNewFilter = getDriver().findElement(elemConditionNewFilter);

        uiActions.clickOnElement(elemFreeShippingFilter);
        getWebDriverWait().until(ExpectedConditions.stalenessOf(freeShippingFilter));

        uiActions.clickOnElement(elemConditionNewFilter);
        getWebDriverWait().until(ExpectedConditions.stalenessOf(conditionNewFilter));

    }

    public void sortResultsHighToLow(){
        UIActions uiActions = new UIActions();

        uiActions.clickOnElement(elemDropDown);
        uiActions.clickOnElement(elemHighToLowOption);

    }

    public void addProductsBelow15k() {

        UIActions uiActions = new UIActions();

        int maxPages = 5;
        int itemsAdded = 0;

        for (int i = 1; i <= maxPages; i++) {
            System.out.println("Page: " + i);

            // 1. Find all product containers on the current page
            List<WebElement> products = getDriver().findElements(elemProducts);

            for (WebElement product : products) {
                try {
                    // 2. Check Price
                    WebElement priceElement = product.findElement(elemPrice);

                    // Remove commas and convert to integer
                    String priceText = priceElement.getText().replace(",", "").trim();
                    int price = Integer.parseInt(priceText);

                    if (price < 15000) {
                        System.out.println("Found item < 15k: " + price);

                        try {
                            // 3. Add to Cart
                            WebElement addToCartBtn = product.findElement(elemAddToCartBtn);
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addToCartBtn);
                            itemsAdded ++;
                            System.out.println("Item added to cart. Total items added: " + itemsAdded);
                        } catch (Exception e) {
                            System.out.println("The item doesn't have add to cart button.");
                        }

                        Thread.sleep(1500);
                    }

                } catch (Exception e) {
                    // Continue to next product
                    continue;
                }
            }

            // 4. "If no product below 15k EGP move to next page"
            // If we are not on the last page, click 'Next'
            if (i < maxPages) {
                try {
                    uiActions.clickWithJS(elemNextBtn);

                    // Wait for the next page to load
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("No 'Next' button found or last page reached.");
                    break; // Exit loop if no next button
                }
            }
        }

        // 5. Validation
        verifyProductsAddedToCart(itemsAdded);

    }

    public void openCart(){

        UIActions uiActions = new UIActions();
        uiActions.clickOnElement(elemCartCount);
    }

    // Assertions -----------------------------------------------------------------------------------------------------
    public void verifyProductsAddedToCart(int itemsAdded) {
        try {
            getWebDriverWait().until(ExpectedConditions.textToBe(
                    elemCartCount,
                    String.valueOf(itemsAdded)
            ));
        } catch (Exception e) {
            System.out.println("Cart count did not update to " + itemsAdded + " within the timeout.");
        }

        WebElement cartCount = getDriver().findElement(elemCartCount);
        int amazonCartCount = Integer.parseInt(cartCount.getText().trim());

        System.out.println("Expected Count: " + itemsAdded);
        System.out.println("Amazon Cart Count: " + amazonCartCount);

        Assert.assertEquals(amazonCartCount, itemsAdded, "Cart count does not match the number of items added.");
    }

}
