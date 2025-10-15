package com.ecosio.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BasePage {
    private final WebDriverWait wait;

    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItem = By.className("inventory_item");
    private final By sortDropdown = By.className("product_sort_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
    }

    @Step
    public void addItemToCartByName(String name) {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItem));
        for (WebElement it : items) {
            String title = it.findElement(By.className("inventory_item_name")).getText().trim();
            if (title.equalsIgnoreCase(name)) {
                it.findElement(By.tagName("button")).click();
                return;
            }
        }
        throw new RuntimeException("Item not found: " + name);
    }

    @Step
    public void removeItemFromCartByName(String name) {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItem));
        for (WebElement it : items) {
            String title = it.findElement(By.className("inventory_item_name")).getText().trim();
            if (title.equalsIgnoreCase(name)) {
                it.findElement(By.tagName("button")).click();
                return;
            }
        }
        throw new RuntimeException("Item not found: " + name);
    }

    @Step
    public void sortBy(String value) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)));
        select.selectByValue(value);
    }
}