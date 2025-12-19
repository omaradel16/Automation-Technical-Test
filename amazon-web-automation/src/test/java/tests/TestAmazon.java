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
        test.info("");

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