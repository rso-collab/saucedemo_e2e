package com.example.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void createInstance(String browser, boolean headless) {
        WebDriver driver;
        if (browser == null) browser = "chrome";

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffo = new FirefoxOptions();
                if (headless) ffo.addArguments("--headless", "--width=1920", "--height=1080");
                ffo.addPreference("signon.rememberSignons", false);
                ffo.addPreference("extensions.enabledScopes", 0);
                ffo.addPreference("browser.aboutConfig.showWarning", false);
                driver = new FirefoxDriver(ffo);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo = new EdgeOptions();
                if (headless) eo.addArguments("--headless=new", "--window-size=1920,1080");
                eo.addArguments("--disable-notifications", "--disable-save-password-bubble",
                        "--disable-infobars", "--disable-extensions", "--disable-popup-blocking");
                eo.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new EdgeDriver(eo);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new", "--window-size=1920,1080");
                co.addArguments("--disable-notifications", "--disable-save-password-bubble",
                        "--disable-infobars", "--disable-extensions", "--disable-popup-blocking",
                        "--guest");
                co.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(co);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driverThread.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
