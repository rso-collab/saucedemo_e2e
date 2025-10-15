package com.ecosio.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutStepTwoPage extends BasePage {
    private final WebDriverWait wait;

    private final By summaryContainer = By.id("checkout_summary_container");
    private final By itemPrices = By.className("inventory_item_price");
    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishBtn = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step
    public void waitForPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(summaryContainer));
        } catch (Exception e) {
            // Fallback: wait for URL change to step two
            wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(summaryContainer));
        }
    }

    @Step
    public double getDisplayedItemTotal() {
        String text = getText(itemTotal);
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    @Step
    public double sumOfIndividualPrices() {
        List<WebElement> prices = driver.findElements(itemPrices);
        return prices.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("[^0-9.]", ""))
                .mapToDouble(Double::parseDouble)
                .sum();
    }

    @Step
    public void finish() {
        driver.findElement(finishBtn).click();
    }
}