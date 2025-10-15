package com.example.saucedemo.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX = 1; // retries
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX) {
            count++;
            System.out.println("Retrying test " + result.getName() + " (attempt " + (count + 1) + ")");
            return true;
        }
        return false;
    }
}
