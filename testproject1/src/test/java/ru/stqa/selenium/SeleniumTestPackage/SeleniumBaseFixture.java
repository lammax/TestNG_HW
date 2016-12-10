package ru.stqa.selenium.SeleniumTestPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

@Test
public class SeleniumBaseFixture {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver = null;
    public WebDriverWait wait = null;

    @BeforeSuite(groups = "selenium")
    public void baseSetup() {
        System.out.println(">>> SeleniumBaseFixture BeforeSuite");
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));

    }

    @AfterSuite(groups = "selenium")
    public void baseTearDown() {
        System.out.println("<<< SeleniumBaseFixture AfterSuite");
/*
        driver.quit();
        driver = null;
*/
        wait = null;
    }

}
