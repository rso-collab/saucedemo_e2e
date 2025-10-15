package com.ecosio.saucedemo.tests;

import com.ecosio.saucedemo.pages.LoginPage;
import com.ecosio.saucedemo.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LockedOutUserLoginNegativeTest extends BaseTest {

    @Test(description = "Negative: Locked out user cannot log in")
    public void lockedOutUserCannotLogin() {
        logger.info("Starting LockedOutUserLoginNegativeTest...");
        LoginPage login = new LoginPage(getDriver());
        login.login("locked_out_user", "secret_sauce");
        String msg = login.getErrorMessage();
        Assert.assertTrue(msg.toLowerCase().contains("locked out"), "Error should indicate the user is locked out. Actual: " + msg);
    }
}
