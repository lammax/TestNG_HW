package ru.stqa.selenium.SeleniumTestPackage;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import static org.junit.runners.Suite.*;

import ru.stqa.selenium.BaseFeatures.MyJUnitCategories;

@RunWith(Categories.class)
@IncludeCategory({ MyJUnitCategories.PositiveTests.class, MyJUnitCategories.NegativeTests.class })
@SuiteClasses({ SeleniumTest.class })

public class SeleniumCategorizedTestSuite {

}
