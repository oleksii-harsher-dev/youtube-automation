package com.youtube.appium.screens;

import com.youtube.appium.screens.widgets.SubscribeBottomsheet;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ChannelScreen extends BaseScreen {
    private AndroidDriver driver;
    private static final By SUBSCRIBE_BUTTON =
            AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc, 'Subscribe to')]");

    public ChannelScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SubscribeBottomsheet clickSubscribe() {
        driver.findElement(SUBSCRIBE_BUTTON).click();
        return new SubscribeBottomsheet(driver);
    }
}
