package com.youtube.appium.tests;

import com.youtube.appium.base.BaseTest;
import com.youtube.appium.screens.widgets.SubscribeBottomsheet;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.youtube.appium.screens.*;
import com.youtube.appium.utils.RandomUtil;

public class YoutubeSubscribeTest extends BaseTest {

    @Test
    public void subscribeWithoutLoginTest() {

        HomeScreen home = new HomeScreen(driver);
        SearchScreen search = new SearchScreen(driver);
        ResultsScreen results = new ResultsScreen(driver);
        VideoScreen video = new VideoScreen(driver);
        ChannelScreen channel = new ChannelScreen(driver);

        Assert.assertTrue(home.isDisplayed());

        Assert.assertEquals(
                driver.getCurrentPackage(),
                "com.google.android.youtube");

        driver.rotate(ScreenOrientation.PORTRAIT);

        String searchValue =
                RandomUtil.randomSearchRequest();

        home.openSearch();
        search.search(searchValue);
        search.selectSecondSuggestion();
        results.openFourthVideo();
        video.openAuthorChannel();
        SubscribeBottomsheet subscribeBottomsheet = channel.clickSubscribe();

        Assert.assertTrue(
                subscribeBottomsheet.isDisplayed());
    }
}