# SauceDemo E2E Automation Framework - Ecosio 🚀

This is a **Java Selenium** automation framework for testing the [SauceDemo](https://www.saucedemo.com/)
application.

---

## 🧩 Tech Stack

- **Language:** Java 17
- **Test Runner:** TestNG
- **Automation:** Selenium WebDriver
- **Build Tool:** Maven
- **Reporting:** Allure
- **Logging:** Log4j2
- **Parallel Execution:** TestNG suite XML (`testng-parallel.xml`)

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites

- Java 17
- Maven
- Browsers: Chrome, Firefox, Edge

## 🧪 Running Tests

### Run Standard Suite - Sequential run of all tests one by one on Chrome (by default)

```bash
mvn clean test 
```

### Run in Parallel (Recommended)

```bash
mvn clean test "-Dsuite=testng-parallel.xml" -Dheadless=true -Dbrowser=firefox
```

### Run Headless on Chrome

```bash
mvn clean test "-Dsuite=testng.xml" -Dheadless=true -Dbrowser=chrome
```

### Supported Parameters

| Parameter  | Description                          | Default      |
|------------|--------------------------------------|--------------|
| `browser`  | Browser to use (`chrome`, `firefox`) | `chrome`     |
| `headless` | Run browser in headless mode         | `false`      |
| `suite`    | TestNG XML file                      | `testng.xml` |

---

## 📊 Allure Reporting

After running tests, generate and view the interactive report:

```bash
mvn allure:serve
```

Report files will appear under `target/allure-report`.

---

## 🪵 Logging

Logs are printed to the console and saved in:

```
logs/framework.log
```

Logging configuration can be customized in:

```
src/main/resources/log4j2.xml
```

---

## 🧱 Project Structure

```
src/
  main/java/com/ecosio/saucedemo/
    pages/           # Page Object classes
    components/      # Reusable UI components
    utils/           # BaseTest, ConfigReader, WebDriverFactory, etc.
  test/java/com/ecosio/saucedemo/
    tests/           # TestNG test classes
    utils/           # RetryAnalyzer, RetryListener, etc.

pom.xml              # Maven configuration with Allure & Log4j2
testng.xml           # Standard test suite
testng-parallel.xml  # Parallel execution suite
```

---

## ✅ Summary

This framework is modular, extendable, and CI/CD ready.  
It supports multi-browser testing, parallel execution, and Allure reporting for detailed analysis.

**Run → Report → Analyze → Iterate 🔁**
