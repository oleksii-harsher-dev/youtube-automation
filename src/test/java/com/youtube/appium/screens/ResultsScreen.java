
package com.youtube.appium.screens;

import com.youtube.appium.screens.widgets.ListVideoItem;
import com.youtube.appium.utils.ScrollUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ResultsScreen extends BaseScreen {

    private static final By FILTER_PILLS_BAR =
            AppiumBy.xpath(
                    "//android.support.v7.widget.RecyclerView[@content-desc='filters']"
            );

    private static final By FILTER_PILLS =
            AppiumBy.xpath(".//android.widget.LinearLayout");

    private static final By RESULTS_RECYCLER =
            AppiumBy.id("com.google.android.youtube:id/results");

    private static final By FILTER_TEXT =
            AppiumBy.xpath(".//android.widget.TextView");

    private static final String VIDEOS_FILTER = "Videos";
    private static final int TARGET_VIDEO_NUMBER = 4;
    private static final int MAX_SCROLL_ATTEMPTS = 20;

    public ResultsScreen(AndroidDriver driver) {
        super(driver);
    }

    public void openFourthVideo() {
        showFilterPills();
        selectFilter(VIDEOS_FILTER);
        scrollUntilFourthVideoIsDisplayedAndClick();
    }

    private void showFilterPills() {
        ScrollUtil.scrollDown(driver, RESULTS_RECYCLER, 0.9);
        ScrollUtil.scrollUp(driver, RESULTS_RECYCLER, 0.9);
    }

    private void selectFilter(String filterToSelect) {
        WebElement filterBar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(FILTER_PILLS_BAR)
        );

        List<WebElement> filters = filterBar.findElements(FILTER_PILLS);

        for (WebElement filter : filters) {
            String filterText = filter.findElement(FILTER_TEXT).getText();

            if (filterText.equalsIgnoreCase(filterToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(filter)).click();
                return;
            }
        }

        throw new NoSuchElementException(
                "Filter not found: " + filterToSelect
        );
    }

    private void scrollUntilFourthVideoIsDisplayedAndClick() {
        Set<String> encounteredVideos = new LinkedHashSet<>();

        for (int attempt = 0; attempt < MAX_SCROLL_ATTEMPTS; attempt++) {
            List<ListVideoItem> videoItems = getVideoItems();

            for (ListVideoItem video : videoItems) {
                try {
                    if (video.isSponsored()) {
                        continue;
                    }

                    wait.until(
                            ExpectedConditions.visibilityOf(video.getRoot())
                    );

                    String title = video.getTitle();

                    if (title == null || title.isBlank()) {
                        continue;
                    }

                    encounteredVideos.add(title);

                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    break;
                }

                if (encounteredVideos.size() == TARGET_VIDEO_NUMBER) {
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    video.getRoot()
                            )
                    );

                    video.click();
                    return;
                }
            }

            boolean canScrollMore = ScrollUtil.scroll(
                    driver,
                    RESULTS_RECYCLER,
                    "down",
                    0.6
            );

            if (!canScrollMore) {
                break;
            }
        }

        throw new AssertionError(
                "Could not find the fourth non-sponsored video."
        );
    }

    private List<ListVideoItem> getVideoItems() {
        String videoItemsXPath = "//*[@resource-id='com.google.android.youtube:id/results']/*[@class='android.view.ViewGroup']";

        List<WebElement> elements = driver.findElements(
                AppiumBy.xpath(videoItemsXPath)
        );

        return elements.stream()
                .map(element -> new ListVideoItem(driver, element))
                .toList();
    }
}
