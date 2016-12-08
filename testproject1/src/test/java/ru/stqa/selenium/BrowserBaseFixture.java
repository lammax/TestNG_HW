package ru.stqa.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class BrowserBaseFixture {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();
    public WebDriver driver = null;
    public WebDriverWait wait;

    @BeforeClass
    public void baseSetup() {
        System.out.println("Base setup");
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                            driver.quit();
                            driver = null;
                        }
                    }
                )
        );
    }

    @AfterClass
    public void baseTearDown() {
        System.out.println("Base teardown");
        driver.quit();
        driver = null;
        wait = null;
        tlDriver = null;
    }

}
