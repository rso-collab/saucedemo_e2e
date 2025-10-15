package com.example.saucedemo.tests;

import com.example.saucedemo.components.HeaderComponent;
import com.example.saucedemo.pages.CartPage;
import com.example.saucedemo.pages.CheckoutStepOnePage;
import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.pages.LoginPage;
import com.example.saucedemo.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutMissingPostalCodeNegativeTest extends BaseTest {

    @Test(description = "Negative: Missing postal code shows validation error on checkout")
    public void missingPostalCodeShowsError() {
        logger.info("Starting CheckoutMissingPostalCodeNegativeTest...");
        LoginPage login = new LoginPage(getDriver());
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.waitForPage();
        inventory.addItemToCartByName("Sauce Labs Onesie");

        HeaderComponent header = new HeaderComponent(getDriver());
        header.openCart();

        CartPage cart = new CartPage(getDriver());
        cart.waitForPage();
        cart.proceedToCheckout();

        CheckoutStepOnePage step1 = new CheckoutStepOnePage(getDriver());
        step1.fillInfo("Alex", "Taylor", ""); // Missing postal code
        step1.clickContinue();

        String msg = step1.getErrorMessage();
        Assert.assertTrue(msg.toLowerCase().contains("postal code is required"),
                "Expected postal-code required error. Actual: " + msg);
    }
}
