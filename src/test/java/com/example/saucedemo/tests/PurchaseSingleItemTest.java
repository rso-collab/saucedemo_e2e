package com.example.saucedemo.tests;

import com.example.saucedemo.components.HeaderComponent;
import com.example.saucedemo.pages.*;
import com.example.saucedemo.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseSingleItemTest extends BaseTest {

    @Test(description = "E2E: Standard user purchases a single item successfully")
    public void purchaseSingleItem() {
        logger.info("Starting PurchaseSingleItemTest...");
        LoginPage login = new LoginPage(getDriver());
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(getDriver());
        inventory.waitForPage();
        inventory.addItemToCartByName("Sauce Labs Backpack");

        HeaderComponent header = new HeaderComponent(getDriver());
        Assert.assertEquals(header.getCartBadgeCount(), 1, "Cart badge should show 1 item");

        header.openCart();

        CartPage cart = new CartPage(getDriver());
        cart.waitForPage();
        Assert.assertEquals(cart.getItemCount(), 1, "Cart should contain 1 item");
        cart.proceedToCheckout();

        CheckoutStepOnePage step1 = new CheckoutStepOnePage(getDriver());
        step1.fillInfo("John", "Doe", "12345");
        step1.clickContinue();

        CheckoutStepTwoPage step2 = new CheckoutStepTwoPage(getDriver());
        step2.waitForPage();
        step2.finish();

        CheckoutCompletePage done = new CheckoutCompletePage(getDriver());
        Assert.assertEquals(done.getCompletionHeader(), "Thank you for your order!", "Completion message should match");
    }
}
