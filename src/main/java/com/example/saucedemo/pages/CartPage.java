package com.example.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {
    private final WebDriverWait wait;

    private final By cartList = By.id("cart_contents_container");
    private final By cartItem = By.className("cart_item");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartList));
    }

    @Step
    public int getItemCount() {
        List<WebElement> items = driver.findElements(cartItem);
        return items.size();
    }

    @Step
    public void removeItemByName(String name) {
        List<WebElement> items = driver.findElements(cartItem);
        for (WebElement it : items) {
            String title = getText(By.className("inventory_item_name")).trim();
            if (title.equalsIgnoreCase(name)) {
                it.findElement(By.tagName("button")).click();
                return;
            }
        }
        throw new RuntimeException("Item not found in cart: " + name);
    }

    @Step
    public void proceedToCheckout() {
        click(checkoutBtn);
    }
}