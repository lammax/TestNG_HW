package ru.stqa.selenium.SeleniumTestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class SeleniumTest extends SeleniumBaseFixture {


    @BeforeClass(groups = {"selenium"})
    private void classSetUp() {
        System.out.println("@BeforeClass getPage");
        driver.get("http://mail.ru");
    }

    @AfterMethod(groups = {"selenium"})
    private void classTearDown() throws InterruptedException {
        System.out.println("@AfterMethod wait 3 sec");
        Thread.sleep(3 * 1000);
    }


    @Test(groups = {"positive", "selenium"})
    public void _1_Positive_Test() {

        System.out.println("SeleniumTest -> positive test1 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By searchFieldSelector = By.cssSelector("form.search__form input[type=text]");
        check.assertPresentElementLocated(searchFieldSelector);
        driver.findElement(searchFieldSelector).sendKeys("Some search text");

    }

    @Test(groups = {"positive", "selenium"})
    public void _2_Positive_Test() {

        System.out.println("SeleniumTest -> positive test2 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By newsLinkSelector = By.cssSelector("div#portal-headline > table > tbody > tr > td:first-child a:nth-child(7)");
        check.assertPresentElementLocated(newsLinkSelector);
        driver.findElement(newsLinkSelector).click();

    }

    @Test(groups = {"positive", "selenium"})
    public void _3_Positive_Test() {

        System.out.println("SeleniumTest -> positive test3 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By firstJSNewsSelector = By.cssSelector("div.daynews.js-topnews a:nth-child(1)");
        WebElement firstJSNews = driver.findElements(firstJSNewsSelector).get(0);
        check.assertDisplayed(firstJSNews);
        firstJSNews.click();

    }

    @Test(groups = {"negative", "selenuim"})
    public void _1_Negative_Test() {

        System.out.println("SeleniumTest -> negative test1 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By somethingWrongSelector = By.cssSelector("div#wrong1");
        check.assertPresentElementLocated(somethingWrongSelector);

    }

    @Test(groups = {"negative", "selenuim"})
    public void _2_Negative_Test() {

        System.out.println("SeleniumTest -> negative test2 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By somethingWrongSelector = By.cssSelector("div#wrong2");
        check.assertPresentElementLocated(somethingWrongSelector);

    }
    @Test(groups = {"negative", "selenuim"})
    public void _3_Negative_Test() {

        System.out.println("SeleniumTest -> negative test3 running");

        SeleniumAssertion check = new SeleniumAssertion(driver);

        By somethingWrongSelector = By.cssSelector("div#wrong3");
        check.assertPresentElementLocated(somethingWrongSelector);

    }

}
