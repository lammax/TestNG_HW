package ru.stqa.selenium.FileSystemTestPackage;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesystemBaseFixture {

    protected static Path tempDirectory = null;
    protected static SoftAssert sftA = null;

//    groups = {"positive", "negative", "filesystem", "dataprovider"}
    @BeforeSuite(alwaysRun = true)
    public void baseFSSetup() throws IOException{

        System.out.println("@BeforeClass baseFSSetup");

        sftA = new SoftAssert();

        Path rootDirectory = Paths.get(".\\TempDir");
        Boolean directoryNotExists = Files.notExists(rootDirectory);

        if (directoryNotExists) {
            tempDirectory = Files.createDirectory(rootDirectory);
        } else {
            tempDirectory = rootDirectory;
        }

    }

//    groups = {"positive", "negative", "filesystem", "dataprovider"}
    @AfterSuite(alwaysRun = true)
    public void baseFSTeardown() throws IOException {

        System.out.println("@AfterClass baseFSTeardown");

        Boolean isDirectoryDeleted = Files.deleteIfExists(tempDirectory);
        sftA.assertTrue(isDirectoryDeleted, "Temp directory was not deleted.");

        tempDirectory = null;

        sftA.assertAll();
        sftA = null;

    }

}
