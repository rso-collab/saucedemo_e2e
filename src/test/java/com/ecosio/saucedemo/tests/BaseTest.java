package com.ecosio.saucedemo.tests;

import com.ecosio.saucedemo.utils.ConfigReader;
import com.ecosio.saucedemo.utils.WebDriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    // protected WebDriver driver;

    protected WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }

    @BeforeClass
    public void setUp() {
        logger.info("Initializing WebDriver...");
        String browser = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        String baseUrl = ConfigReader.get("baseUrl");

        Allure.parameter("Browser", browser);
        Allure.label("browser", browser);

        WebDriverFactory.createInstance(browser, headless);
        logger.info("WebDriver initialized: {}{}", browser, headless ? " (headless)" : "");

        getDriver().get(baseUrl);
        logger.info("Navigated to {} | Title: {}", baseUrl, getDriver().getTitle());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            WebDriverFactory.quitDriver();
            logger.info("WebDriver closed successfully.");
        }
    }
}
