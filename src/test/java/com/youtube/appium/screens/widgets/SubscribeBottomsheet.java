package com.youtube.appium.screens.widgets;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubscribeBottomsheet {

    private final AndroidDriver driver;

    private static final By SUCCESSFUL_SUBSCRIBE_TOAST =
            AppiumBy.id("com.google.android.youtube:id/message");

    public SubscribeBottomsheet(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        try {
            String toastMessage = new WebDriverWait(driver, Duration.ofSeconds(7))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            SUCCESSFUL_SUBSCRIBE_TOAST
                    ))
                    .getText();

            return "Subscription added".equals(toastMessage);

        } catch (TimeoutException e) {
            return false;
        }
    }
}