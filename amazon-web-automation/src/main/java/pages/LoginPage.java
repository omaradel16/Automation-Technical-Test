package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.UIActions;

import static utils.BaseTest.getDriver;
import static utils.BaseTest.getWebDriverWait;

public class LoginPage {

    private static final By elemEmailField = By.id("ap_email_login");
    private static final By elemContinueBtn = By.id("continue");
    private static final By elemPasswordField = By.id("ap_password");
    private static final By elemSignInBtn= By.id("signInSubmit");


    public void logIn(String email, String password) {

        UIActions uiActions = new UIActions();

        uiActions.clearAndText(elemEmailField, email);
        uiActions.clickOnElement(elemContinueBtn);

        uiActions.clearAndText(elemPasswordField, password);
        uiActions.clickOnElement(elemSignInBtn);

    }
}
