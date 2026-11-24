package com.youtube.appium.screens;

import com.youtube.appium.screens.widgets.AuthorInfoVideoItem;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VideoScreen extends BaseScreen {
    private AndroidDriver driver;
    private static final By STICKY_HEADER_CONTAINER =
            AppiumBy.id(
                    "com.google.android.youtube:id/sticky_header_container"
            );
    private static final By SKIP_BUTTON = AppiumBy.id(
            "com.google.android.youtube:id/skip_ad_button_text"
    );

    public VideoScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AuthorInfoVideoItem getAuthor() {
        return new AuthorInfoVideoItem(
                driver.findElement(STICKY_HEADER_CONTAINER)
        );
    }

    public void openAuthorChannel() {
        handleSponsoredVideoOpened();
        handleSponsoredVideoOpened();
        getAuthor().clickAuthorAvatar();
    }

    private void handleSponsoredVideoOpened() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(SKIP_BUTTON)).click();

        } catch (TimeoutException e) {
            System.out.println("Skip button was not displayed.");
        }
    }
}
