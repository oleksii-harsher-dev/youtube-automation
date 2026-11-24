package com.youtube.appium.screens.widgets;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthorInfoVideoItem {

    private final WebElement root;
    private static final By AUTHOR_AVATAR_PREVIEW =
            AppiumBy.xpath(
                    ".//*[contains(@content-desc,'subscribers')]"
            );

    public AuthorInfoVideoItem(WebElement root) {
        this.root = root;
    }

    public WebElement getAuthorAvatar() {
        return root.findElement(AUTHOR_AVATAR_PREVIEW);
    }

    public void clickAuthorAvatar() {
        getAuthorAvatar().click();
    }
}