package com.ecosio.saucedemo.components;

import com.ecosio.saucedemo.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) return 0;
        try {
            return Integer.parseInt(badges.get(0).getText().trim());
        } catch (NumberFormatException e) {
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
