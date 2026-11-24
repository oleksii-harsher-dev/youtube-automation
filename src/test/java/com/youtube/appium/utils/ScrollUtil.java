
package com.youtube.appium.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.Map;

public final class ScrollUtil {

    public static boolean scroll(
            AndroidDriver driver,
            By containerLocator,
            String direction,
            double percent
    ) {
        WebElement container = driver.findElement(containerLocator);
        Rectangle rect = container.getRect();

        return (boolean) driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", rect.x,
                        "top", rect.y,
                        "width", rect.width,
                        "height", rect.height,
                        "direction", direction,
                        "percent", percent
                )
        );
    }

    public static void scrollDown(
            AndroidDriver driver,
            By containerLocator,
            double percent
    ) {
        scroll(driver, containerLocator, "down", percent);
    }

    public static void scrollUp(
            AndroidDriver driver,
            By containerLocator,
            double percent
    ) {
        scroll(driver, containerLocator, "up", percent);
    }
}
