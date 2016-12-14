package ru.stqa.selenium.FileSystemTestPackage;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import ru.stqa.selenium.BaseFeatures.DataProviders;
import ru.stqa.selenium.BaseFeatures.MyJUnitCategories;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.Files.*;

@RunWith(DataProviderRunner.class)
public class FileSystemTest extends FilesystemBaseFixture implements MyJUnitCategories {

    private Boolean bool = false;
    private Path tempFile = null;
    private Boolean doNotDeleteFile = false;

    @After
    public void clearTempFile() throws IOException, InterruptedException {
        System.out.println("@AfterMethod clearTempFile");
        if (doNotDeleteFile) {
            doNotDeleteFile = false;
            bool = false;
        } else {
            Thread.sleep(1 * 1000);
            bool = deleteIfExists(tempFile);
        }
        sftA.assertTrue(bool, "File didn't deleted!");
        tempFile = null;
    }

    @Test
    @Category({ PositiveTests.class, FilesystemTests.class })
    public void _1_Positive_Test() throws IOException {

        tempFile = createTempFile(tempDirectory,"tempFile", ".txt");
        sftA.assertTrue(isWritable(tempFile), "_1_Positive_Test: It's not a writable file!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_1_Positive_Test - finished");

    }

    @Test
    @Category({ PositiveTests.class, FilesystemTests.class })
    public void _2_Positive_Test() throws IOException {

        tempFile = createTempFile(tempDirectory,"tempFile", ".txt");
        sftA.assertTrue(isReadable(tempFile), "_2_Positive_Test: It's not readable!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_2_Positive_Test - finished");
    }

    @Test
    @Category({ PositiveTests.class, FilesystemTests.class })
    public void _3_Positive_Test() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testPositive2.txt");
        tempFile =  createFile(fullFilePath);
        sftA.assertFalse(notExists(tempFile), "_3_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_3_Positive_Test - finished");
    }

    @Test
    @UseDataProvider(value = "fileNames", location = DataProviders.class)
    @Category({ PositiveTests.class, FilesystemTests.class })
    public void _4_Positive_Test_Data_provider(String fileName) throws IOException {

        Path fullFilePath = tempDirectory.resolve(fileName);
        tempFile =  createFile(fullFilePath);
        sftA.assertFalse(notExists(tempFile), "_4_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_4_Positive_Test for file -'" + fileName + "'- finished");

    }

    @Test
    @UseDataProvider(value = "loadFileNames", location = DataProviders.class)
    @Category({ PositiveTests.class, FilesystemTests.class })
    public void _5_Positive_Test_Data_provider(String fileName) throws IOException {

        Path fullFilePath = tempDirectory.resolve(fileName);
        tempFile =  createFile(fullFilePath);
        sftA.assertFalse(notExists(tempFile), "_5_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_5_Positive_Test for file -'" + fileName + "'- finished");

    }



    @Test
    @Category({ NegativeTests.class, FilesystemTests.class })
    public void _1_Negative_Test() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testFile1.txt");
        tempFile =  createFile(fullFilePath);
        doNotDeleteFile = true;

        Boolean isPathDirectory = isDirectory(tempFile, LinkOption.NOFOLLOW_LINKS);

        sftA.assertTrue(isPathDirectory, "_1_Negative_Test: It's not a directory. It's a file!");

        System.out.println("_1_Negative_Test - finished");
    }

    @Test
    @Category({ NegativeTests.class, FilesystemTests.class })
    public void _2_Negative_Test_Wrong_Filename() throws IOException {

        Path fullFilePath = null;

        String wrongCharacters = "/:*?<>|";
        char anyWrongCharacter = wrongCharacters.charAt((int)(Math.round(Math.random() * (wrongCharacters.length() - 1))));

        try {
            fullFilePath = tempDirectory.resolve("testWrongFilename" + anyWrongCharacter + ".txt");
            tempFile =  createFile(fullFilePath);
        } catch(InvalidPathException ex) {
            sftA.assertTrue(false, "_2_Negative_Test: " + ex);
            doNotDeleteFile = true;
        } catch (NoSuchFileException ex2) {
            sftA.assertTrue(false, "_2_Negative_Test: " + ex2);
            doNotDeleteFile = true;
        }

        System.out.println("_2_Negative_Test - finished");
    }

    @Test
    @Category({ NegativeTests.class, FilesystemTests.class })
    public void _3_Negative_Test_File_Already_Exists() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testFile1.txt");

        Boolean fileNotExists = notExists(fullFilePath);
        sftA.assertTrue(fileNotExists, "_3_Negative_Test: File '" + fullFilePath.toString() + "' already exists!");

        if (fileNotExists) {
            tempFile =  createFile(fullFilePath);
        } else {
            tempFile = fullFilePath;
        }

        System.out.println("_3_Negative_Test - finished");
    }

}
