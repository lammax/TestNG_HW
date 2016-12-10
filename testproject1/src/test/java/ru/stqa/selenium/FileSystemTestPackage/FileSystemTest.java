package ru.stqa.selenium.FileSystemTestPackage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileSystemTest extends FilesystemBaseFixture {

    private Boolean bool = false;
    Path tempFile = null;

    @AfterMethod(groups = {"positive", "negative"})
    private void clearTempFile() throws IOException {
        System.out.println("@AfterMethod clearTempFile");
        bool = Files.deleteIfExists(tempFile);
        sftA.assertTrue(bool, "File didn't deleted!");
        tempFile = null;
    }

    @Test(groups = "positive")
    public void _1_Positive_Test() throws IOException {

        tempFile = Files.createTempFile(tempDirectory,"", "");
        sftA.assertTrue(Files.isWritable(tempFile), "_1_Positive_Test: It's not a writable file!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_1_Positive_Test - finished");

    }

    @Test(groups = "positive")
    public void _2_Positive_Test() throws IOException {

        tempFile = Files.createTempFile(tempDirectory,"", "");
        sftA.assertTrue(Files.isReadable(tempFile), "_2_Positive_Test: It's not readable!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_2_Positive_Test - finished");
    }

    @Test(groups = "negative")
    public void _1_Negative_Test() throws IOException {

        tempFile = Files.createTempFile(tempDirectory,"", "");

        sftA.assertFalse(Files.isDirectory(tempFile, LinkOption.NOFOLLOW_LINKS), "_1_Negative_Test: It's not a file. It's a directory!");

        System.out.println("_1_Negative_Test - finished");
    }

}
