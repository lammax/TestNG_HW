package ru.stqa.selenium.FileSystemTestPackage;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemBaseFixture {

    protected static Path tempDirectory = null;
    protected static SoftAssert sftA = null;

    @ClassRule
    public static ExternalResource driverRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
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

        @Override
        protected void after() {
            System.out.println("@AfterClass baseFSTeardown");

            Boolean isDirectoryDeleted = null;
            try {
                isDirectoryDeleted = Files.deleteIfExists(tempDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sftA.assertTrue(isDirectoryDeleted, "Temp directory was not deleted.");

            tempDirectory = null;

            sftA.assertAll();
            sftA = null;
        }
    };

}
