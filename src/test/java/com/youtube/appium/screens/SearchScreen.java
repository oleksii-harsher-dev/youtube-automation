package com.youtube.appium.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchScreen extends BaseScreen {
    private AndroidDriver driver;
    private static final By SEARCH_INPUT =
            AppiumBy.id("com.google.android.youtube:id/search_edit_text");
    private static final By RECYCLER_VIEW =
            AppiumBy.id("com.google.android.youtube:id/results_recycler_view");

    private static final By SUGGESTION_ITEMS =
            AppiumBy.id("com.google.android.youtube:id/linearLayout");

    public SearchScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void search(String value) {
        driver.findElement(SEARCH_INPUT).sendKeys(value);
    }

    public void selectSecondSuggestion() {
        WebElement recyclerView = driver.findElement(RECYCLER_VIEW);
        List<WebElement> items = recyclerView.findElements(SUGGESTION_ITEMS);
        items.get(1).click();
    }
}
