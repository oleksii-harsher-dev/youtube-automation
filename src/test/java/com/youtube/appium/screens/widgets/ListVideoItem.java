package com.youtube.appium.screens.widgets;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListVideoItem {

    private final WebElement root;

    private static final By SPONSORED_LABEL = AppiumBy.xpath(
            ".//android.view.ViewGroup[contains(@content-desc,'Sponsored')]"
    );
    private static final By TITLE_LABEL =
            AppiumBy.xpath(".//*[@content-desc]");
    private final AndroidDriver driver;


    public ListVideoItem(AndroidDriver driver, WebElement root) {
        this.driver = driver;
        this.root = root;
    }

    public boolean isSponsored() {
        return !root.findElements(SPONSORED_LABEL).isEmpty();
    }

    public void click() {
        root.click();
    }

    public String getTitle() {
        return root.findElement(TITLE_LABEL)
                .getAttribute("content-desc");
    }

    public WebElement getRoot() {
        return root;
    }
}
