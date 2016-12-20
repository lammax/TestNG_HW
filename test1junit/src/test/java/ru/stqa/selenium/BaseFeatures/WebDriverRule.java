package ru.stqa.selenium.BaseFeatures;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


public class WebDriverRule extends ExternalResource implements WebDriver {

    private Capabilities capabilities;

    private WebDriver driver;

    public WebDriverRule(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public void startFreshDriver() {
        System.out.println("Stopping old driver.");

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        driver = new ChromeDriver(capabilities);

    }
    @Override
    protected void before() throws Throwable {
        System.out.println("Starting Chrome browser");
        driver = new ChromeDriver(capabilities);
    }

    @Override
    protected void after() {
        System.out.println("Stopping Chrome browser");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Override
    public void get(String s) {
        this.driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return this.driver.getPageSource();
    }

    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.driver.navigate();
    }

    @Override
    public Options manage() {
        return this.driver.manage();
    }
}
