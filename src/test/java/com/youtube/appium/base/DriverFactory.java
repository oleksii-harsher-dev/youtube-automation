package com.youtube.appium.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    public static AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String DEVICE_UDID = "YOUR_DEVICE_ID";

    private static final String APP_PACKAGE = "com.google.android.youtube";
    private static final String APP_ACTIVITY = "com.google.android.youtube.app.honeycomb.Shell$HomeActivity";

    private static final Duration APP_WAIT_DURATION = Duration.ofSeconds(60);
    private static final int NEW_COMMAND_TIMEOUT = 30;

    public static AndroidDriver createDriver() throws MalformedURLException {
        UiAutomator2Options options = buildOptions();

        return new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
    }

    private static UiAutomator2Options buildOptions() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setUdid(DEVICE_UDID);
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);
        options.setAppWaitActivity(APP_ACTIVITY);
        options.setAutoGrantPermissions(true);
        options.setAppWaitDuration(APP_WAIT_DURATION);
        options.setCapability("appium:newCommandTimeout", NEW_COMMAND_TIMEOUT);
        options.setCapability("noReset", true);

        return options;
    }
}
