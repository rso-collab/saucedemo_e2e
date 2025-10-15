package com.example.saucedemo.components;

import com.example.saucedemo.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent extends BasePage {
    private final WebDriverWait wait;

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By cartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By resetLink = By.id("reset_sidebar_link");

    public HeaderComponent(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCart() {
        click(cartLink);
    }

    public int getCartBadgeCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.presenceOfElementLocated(cartBadge));
            return Integer.parseInt(badge.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public void openMenu() {
        click(menuButton);
    }

    public void resetAppState() {
        openMenu();
        click(resetLink);
    }

    public void logout() {
        openMenu();
        click(logoutLink);
    }
}
