package com.ecosio.saucedemo.tests;

import com.ecosio.saucedemo.components.HeaderComponent;
import com.ecosio.saucedemo.pages.*;
import com.example.saucedemo.pages.*;
import com.ecosio.saucedemo.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseMultipleItemsTest extends BaseTest {

    @Test(description = "E2E: Purchase multiple items and verify item total equals sum of individual prices")
    public void purchaseMultipleItemsAndVerifyTotal() {
        logger.info("Starting PurchaseMultipleItemsTest...");
        LoginPage login = new LoginPage(getDriver());
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.waitForPage();
        inventory.addItemToCartByName("Sauce Labs Backpack");
        inventory.addItemToCartByName("Sauce Labs Bike Light");
        inventory.addItemToCartByName("Sauce Labs Bolt T-Shirt");

        HeaderComponent header = new HeaderComponent(getDriver());
        Assert.assertEquals(header.getCartBadgeCount(), 3, "Cart badge should show 3 items");

        header.openCart();

        CartPage cart = new CartPage(getDriver());
        cart.waitForPage();
        Assert.assertEquals(cart.getItemCount(), 3, "Cart should contain 3 items");
        cart.proceedToCheckout();

        CheckoutStepOnePage step1 = new CheckoutStepOnePage(getDriver());
        step1.fillInfo("Jane", "Smith", "90210");
        step1.clickContinue();

        CheckoutStepTwoPage step2 = new CheckoutStepTwoPage(getDriver());
        step2.waitForPage();
        double expected = step2.sumOfIndividualPrices();
        double displayed = step2.getDisplayedItemTotal();
        Assert.assertEquals(displayed, expected, 0.01, "Displayed item total should equal the sum of line items");

        step2.finish();

        CheckoutCompletePage done = new CheckoutCompletePage(getDriver());
        Assert.assertEquals(done.getCompletionHeader(), "Thank you for your order!", "Completion message should match");
    }
}
