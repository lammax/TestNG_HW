package ru.stqa.selenium.FileSystemTestPackage;

//import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
//import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesystemBaseFixture {

    public Path tempDirectory = null;
    public SoftAssert sftA = null;

    @BeforeClass(alwaysRun = true)
    public void baseFSSetup() throws IOException{

        System.out.println("@BeforeClass baseFSSetup");

        sftA = new SoftAssert();

        Path rootDirectory = Paths.get(".\\TempDir");
        tempDirectory = Files.createDirectory(rootDirectory);

    }

    @AfterClass(alwaysRun = true)
    public void baseFSTeardown() throws IOException {

        System.out.println("@AfterClass baseFSTeardown");

        Boolean isDirectoryDeleted = Files.deleteIfExists(tempDirectory);
        sftA.assertFalse(isDirectoryDeleted, "Temp directory was not deleted.");

        sftA.assertAll();
        sftA = null;

        tempDirectory = null;
    }

}
