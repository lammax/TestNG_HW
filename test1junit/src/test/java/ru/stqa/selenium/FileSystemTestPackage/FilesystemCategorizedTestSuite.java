package ru.stqa.selenium.FileSystemTestPackage;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import static org.junit.runners.Suite.*;

import ru.stqa.selenium.BaseFeatures.MyJUnitCategories;

@RunWith(Categories.class)
@IncludeCategory({ MyJUnitCategories.DataProviderTests.class })
@SuiteClasses({ FileSystemTest.class })

public class FilesystemCategorizedTestSuite {

}
