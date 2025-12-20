package pages;

import org.openqa.selenium.By;
import utils.UIActions;

public class LoginPage {

    // Locators -------------------------------------------------------------------------------------------------------
    private static final By elemEmailField = By.id("ap_email_login");
    private static final By elemContinueBtn = By.id("continue");
    private static final By elemPasswordField = By.id("ap_password");
    private static final By elemSignInBtn= By.id("signInSubmit");

    // Methods -------------------------------------------------------------------------------------------------------

    /**
     * Performs the full login sequence on Amazon.
     * This method handles the two-step login process:
     * 1. Enters the email address and clicks "Continue".
     * 2. Enters the password and clicks "Sign In".
     * @param email    The registered email address or phone number.
     * @param password The account password.
     */
    public void logIn(String email, String password) {

        UIActions uiActions = new UIActions();

        uiActions.clearAndText(elemEmailField, email);
        uiActions.clickOnElement(elemContinueBtn);

        uiActions.clearAndText(elemPasswordField, password);
        uiActions.clickOnElement(elemSignInBtn);

    }
}
