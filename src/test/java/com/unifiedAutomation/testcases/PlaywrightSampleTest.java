package com.unifiedAutomation.testcases;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.nio.file.Paths;

public class PlaywrightSampleTest {
    static Browser browser;
    static BrowserContext context;

    @BeforeAll
    static void launchBrowser() {
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
            page.navigate("https://www.google.com");
            System.out.println("Navigated to Google.com");

            // Wait for the search box to be available
            page.waitForSelector("textarea[name='q']");

            // Type "playwright" in the search box
            page.fill("textarea[name='q']", "playwright");
            System.out.println("Typed 'playwright' in the search box");

            // Take a screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
            System.out.println("Screenshot saved");

        } finally {
            // Close the page
            page.close();
        }
    }
}

