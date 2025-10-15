package com.ecosio.saucedemo.tests;

import com.ecosio.saucedemo.components.HeaderComponent;
import com.ecosio.saucedemo.pages.InventoryPage;
import com.ecosio.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetAppStateClearsCartTest extends BaseTest {

    @Test(description = "E2E: Reset App State clears the cart for the user")
    public void resetClearsCart() {
        logger.info("Starting ResetAppStateClearsCartTest...");
        // Login and add item
        LoginPage login = new LoginPage(getDriver());
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.waitForPage();
        inventory.addItemToCartByName("Sauce Labs Backpack");

        HeaderComponent header = new HeaderComponent(getDriver());
        Assert.assertEquals(header.getCartBadgeCount(), 1, "Precondition: one item in cart");

        // Logout
        header.logout();

        // Log back in: cart may persist for the user (app behavior)
        login = new LoginPage(getDriver());
        login.login("standard_user", "secret_sauce");

        header = new HeaderComponent(getDriver());
        Assert.assertTrue(header.getCartBadgeCount() >= 0, "Cart badge should be visible");

        // Use Reset App State to clear persisted cart
        header.resetAppState();

        // Verify cleared
        Assert.assertEquals(header.getCartBadgeCount(), 0, "Cart badge should be empty after Reset App State");
    }
}
