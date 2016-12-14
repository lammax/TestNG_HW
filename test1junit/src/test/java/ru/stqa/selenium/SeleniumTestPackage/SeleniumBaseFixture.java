package ru.stqa.selenium.SeleniumTestPackage;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBaseFixture {

    protected static WebDriver driver = null;
    protected static WebDriverWait wait = null;

    public static ExternalResource driverRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("Starting Chrome browser");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

        @Override
        protected void after() {
            System.out.println("Stopping Chrome browser");
            if (driver != null) {
                driver.quit();
                driver = null;
                wait = null;
            }
        }
    };

    public static ExternalResource openPageRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("@Before class getPage");
            driver.get("http://mail.ru");
        }
    };

    @ClassRule
    public static RuleChain rules = RuleChain
            .outerRule(driverRule)
            .around(openPageRule);

}
