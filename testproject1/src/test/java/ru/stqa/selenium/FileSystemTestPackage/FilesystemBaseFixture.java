package ru.stqa.selenium.FileSystemTestPackage;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesystemBaseFixture {

    public Path tempDirectory = null;

    @BeforeSuite(groups = {"positive", "negative", "filesystem"})
    public void baseFSSetup() throws IOException{

        System.out.println("@BeforeClass baseFSSetup");

        Path rootDirectory = Paths.get(".\\TempDir");
        tempDirectory = Files.createDirectory(rootDirectory);

    }

    @AfterSuite(groups = {"positive", "negative", "filesystem"})
    public void baseFSTeardown() throws IOException {

        System.out.println("@AfterClass baseFSTeardown");

        Boolean isDirectoryDeleted = Files.deleteIfExists(tempDirectory);
        Assert.assertTrue(isDirectoryDeleted, "Temp directory was not deleted.");

        tempDirectory = null;
    }

}
