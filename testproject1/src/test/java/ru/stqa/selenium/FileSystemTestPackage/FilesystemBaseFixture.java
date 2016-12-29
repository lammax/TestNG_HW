package ru.stqa.selenium.FileSystemTestPackage;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;

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


//    protected static GenericContainer redis = new GenericContainer();

//    protected static Container.ExecResult res = null;

//    groups = {"positive", "negative", "filesystem", "dataprovider"}
    @BeforeSuite(alwaysRun = true)
    public void baseFSSetup() throws IOException, InterruptedException {

        System.out.println("@BeforeClass baseFSSetup");

//        res = container.execInContainer("lsb_release -a");

//        System.out.println(res.getStdout());

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

//        container = null;

        sftA.assertAll();
        sftA = null;

    }

}
