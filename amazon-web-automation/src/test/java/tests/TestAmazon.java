package tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class TestAmazon extends BaseTest{

    @Test
    public void test_Adding_Items_To_Cart(){

        ExtentTest test = getExtentTest(); // Get the current thread's ExtentTest
        test.info("<b>Test Scenario:</b> Verify Video Games purchase flow with specific filters and price validation");
        test.info("""
                        <pre>
                        <b>Given</b> the user is logged into Amazon Egypt.
                        <b>When</b> they:
                        - navigate to 'All Video Games' from the 'All' sidebar menu.
                        - apply filters for 'Free Shipping' and 'Condition: New'
                        - sort the results by 'Price: High to Low'.
                        - add all products costing less than 15,000 EGP to the cart (up to 5 pages).
                        - proceed to checkout using 'Cash on Delivery'.

                        <b>Then</b> the final Order Total should match the sum of items and shipping fees
                        </pre>
                        """);

        HomePage homePage   = new HomePage();
        LoginPage loginPage = new LoginPage();
        CartPage cartPage = new CartPage();

        homePage.clickSign();
        loginPage.logIn("doyini9397@arugy.com", "123456");

        homePage.navigateToAllVideoGames();
        homePage.filterResults();
        homePage.sortResultsHighToLow();
        homePage.addProductsBelow15k();
        homePage.openCart();
        cartPage.checkout();
        cartPage.verifyTotalCalculation();

    }
}