package pages;

import org.openqa.selenium.By;
import utils.UIActions;

public class HomePage {

    private static final By elemSignIn = By.id("nav-link-accountList");


    public void clickSign() {
        UIActions uiActions = new UIActions();
        uiActions.clickOnElement(elemSignIn);
    }
}
