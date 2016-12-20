package ru.stqa.selenium.SeleniumTestPackage;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.BaseFeatures.UnstableDriverRule;
import ru.stqa.selenium.BaseFeatures.WebDriverRule;

public class SeleniumBaseFixture {

    protected static WebDriverWait wait = null;

    public static WebDriverRule driver = new WebDriverRule(DesiredCapabilities.chrome());

    public static ExternalResource waitRule = new ExternalResource() {

        @Override
        protected void before() throws Throwable {
            System.out.println("Making WebDriverWait");
            wait = new WebDriverWait(driver, 10);
        }

        @Override
        protected void after() {
            System.out.println("Deleting WebDriverWait");
            if (wait != null) {
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

    @Rule
    public UnstableDriverRule unstableDriverRule = new UnstableDriverRule(driver);


    @ClassRule
    public static RuleChain rules = RuleChain
            .outerRule(driver)
            .around(waitRule)
            .around(openPageRule);


}
