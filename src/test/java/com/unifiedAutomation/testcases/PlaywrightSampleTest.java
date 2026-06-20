package com.unifiedAutomation.testcases;

import com.microsoft.playwright.*;
import com.unifiedAutomation.utils.PropertiesLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.nio.file.Paths;
import java.util.*;

public class PlaywrightSampleTest {
    static Browser browser;
    static BrowserContext context;
    static Map<String, Properties> propertiesMap;

    @BeforeAll
    static void init() {
        propertiesMap = PropertiesLoader.load();
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch();
        context = browser.newContext();
    }

    @AfterAll
    static void closeBrowser() {
        context.close();
        browser.close();
    }

    @Test
    void testOpenGoogleAndTypePlaywright() {
        // Create a new page
        Page page = context.newPage();


        try {
            // Navigate to Google
            page.navigate(propertiesMap.get("PlaywrightConfig").getProperty("url"));
            System.out.println("Navigated to Google.com");

            // Wait for the search box to be available
            page.waitForSelector(propertiesMap.get("PlaywrightConfig").getProperty("textarea.css"));

            // Type "playwright" in the search box
            page.fill(propertiesMap.get("PlaywrightConfig").getProperty("textarea.css"), propertiesMap.get("PlaywrightConfig").getProperty("textarea.dataToSearch"));
            System.out.println("Typed 'playwright' in the search box");

            // Take a screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(propertiesMap.get("PlaywrightConfig").getProperty("screenShotFileName"))));
            System.out.println("Screenshot saved");

        } finally {
            // Close the page
            page.close();
        }
    }
}

