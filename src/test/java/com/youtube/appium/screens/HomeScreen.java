package com.youtube.appium.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeScreen extends BaseScreen {
    private AndroidDriver driver;
    private static final By YOUTUBE_LOGO =
            AppiumBy.id("com.google.android.youtube:id/youtube_logo");
    private static final By SEARCH_BUTTON =
            AppiumBy.id("com.google.android.youtube:id/menu_item_1");

    public HomeScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void openSearch() {
        driver.findElement(SEARCH_BUTTON).click();
    }

    public boolean isDisplayed() {
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            YOUTUBE_LOGO
                    )
            );
            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }
}
