package ru.stqa.selenium.BaseFeatures;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class SoftHamkrestAssert extends SoftAssert{

    public <T> void assertThat(final T actual, final Matcher<? super T> matcher) {
        doAssert(new IAssert() {

            @Override
            public void doAssert() {
                MatcherAssert.assertThat(actual, matcher);
            }

            @Override
            public Object getActual() {
                return actual;
            }

            @Override
            public Object getExpected() {
                return null;
            }

            @Override
            public String getMessage() {
                return null;
            }

        });
    }



}
