package ru.stqa.selenium;

import org.testng.annotations.Test;

@Test
public class FirstSampleTest extends BrowserBaseFixture {

    public void test1() {
        System.out.println("FirstSampleTest -> test1 running");
    }

    public void test2() {
        System.out.println("FirstSampleTest -> test2 running");
    }

}
