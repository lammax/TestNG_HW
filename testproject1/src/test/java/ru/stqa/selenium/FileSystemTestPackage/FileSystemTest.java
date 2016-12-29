package ru.stqa.selenium.FileSystemTestPackage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;

import ru.stqa.selenium.BaseFeatures.DataProviders;
import ru.stqa.selenium.BaseFeatures.DataSource;
import ru.stqa.selenium.BaseFeatures.UniversalDataProvider;

import static ru.stqa.selenium.BaseFeatures.DataSource.Type.AUTO;
import static ru.stqa.selenium.BaseFeatures.DataSource.Type.EXCEL;
import static ru.stqa.selenium.BaseFeatures.DataSource.Type.FILE;

public class FileSystemTest extends FilesystemBaseFixture {

    private Boolean bool = false;
    private Path tempFile = null;
    private Boolean doNotDeleteFile = false;

    @AfterMethod(groups = {"positive", "negative", "filesystem"})
    private void clearTempFile() throws IOException, InterruptedException {
        System.out.println("@AfterMethod clearTempFile");
        if (doNotDeleteFile) {
            doNotDeleteFile = false;
            bool = false;
        } else {
            Thread.sleep(1 * 1000);
            bool = Files.deleteIfExists(tempFile);
        }
        sftA.assertTrue(bool, "File didn't deleted!");
        tempFile = null;
    }

    @Test(groups = {"positive", "filesystem"})
    public void _1_Positive_Test() throws IOException {

        tempFile = Files.createTempFile(tempDirectory,"tempFile", ".txt");
        sftA.assertTrue(Files.isWritable(tempFile), "_1_Positive_Test: It's not a writable file!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_1_Positive_Test - finished");

    }

    @Test(groups = {"positive", "filesystem"})
    public void _2_Positive_Test() throws IOException {

        tempFile = Files.createTempFile(tempDirectory,"tempFile", ".txt");
        sftA.assertTrue(Files.isReadable(tempFile), "_2_Positive_Test: It's not readable!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_2_Positive_Test - finished");
    }

    @Test(groups = {"positive", "filesystem"})
    public void _3_Positive_Test() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testPositive2.txt");
        tempFile =  Files.createFile(fullFilePath);
        sftA.assertFalse(Files.notExists(tempFile), "_3_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_3_Positive_Test - finished");
    }

    @Test(groups = {"positive", "filesystem", "dataprovider"}, dataProviderClass = UniversalDataProvider.class, dataProvider = "dataSourceLoader")
    @DataSource(value = "/Data/dataEXLFileNames.xlsx", type = EXCEL)
    public void _4_Positive_Test_Data_provider(String fileName) throws IOException {

        System.out.println(">>> " + fileName);

        Path fullFilePath = tempDirectory.resolve(fileName);
        tempFile =  Files.createFile(fullFilePath);
        sftA.assertFalse(Files.notExists(tempFile), "_4_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_4_Positive_Test for file -'" + fileName + "'- finished");

    }

    @Test(groups = {"positive", "filesystem", "dataprovider"}, dataProviderClass = UniversalDataProvider.class, dataProvider = "dataSourceLoader")
    @DataSource(type = AUTO)
    public void _5_Positive_Test_Data_provider(String fileName) throws IOException {

        Path fullFilePath = tempDirectory.resolve(fileName);
        tempFile =  Files.createFile(fullFilePath);
        sftA.assertFalse(Files.notExists(tempFile), "_5_Positive_Test: File not exists!");

        System.out.println("Temporary file should be created successfully! - " + tempFile.toString());
        System.out.println("_5_Positive_Test for file -'" + fileName + "'- finished");

    }

    /*
    Хорошо, но негативных тестов маловато, один, да и тот не очень негативный.
    Предлагаю добавить тесты для ситуаций, когда файл не создаётся, потому что имя содержит недопустимые символы,
    а также файл не создаётся, потому что уже существует.
*/
    @Test(groups = {"negative", "filesystem"})
    public void _1_Negative_Test() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testFile1.txt");
        tempFile =  Files.createFile(fullFilePath);
        doNotDeleteFile = true;

        Boolean isPathDirectory = Files.isDirectory(tempFile, LinkOption.NOFOLLOW_LINKS);

        sftA.assertTrue(isPathDirectory, "_1_Negative_Test: It's not a directory. It's a file!");

        System.out.println("_1_Negative_Test - finished");
    }

    @Test(groups = {"negative", "filesystem"})
    public void _2_Negative_Test_Wrong_Filename() throws IOException {

        Path fullFilePath = null;

        String wrongCharacters = "/:*?<>|";
        char anyWrongCharacter = wrongCharacters.charAt((int)(Math.round(Math.random() * (wrongCharacters.length() - 1))));

        try {
            fullFilePath = tempDirectory.resolve("testWrongFilename" + anyWrongCharacter + ".txt");
            tempFile =  Files.createFile(fullFilePath);
        } catch(InvalidPathException ex) {
            sftA.assertTrue(false, "_2_Negative_Test: " + ex);
            doNotDeleteFile = true;
        } catch (NoSuchFileException ex2) {
            sftA.assertTrue(false, "_2_Negative_Test: " + ex2);
            doNotDeleteFile = true;
        }

        System.out.println("_2_Negative_Test - finished");
    }

    @Test(groups = {"negative", "filesystem"})
    public void _3_Negative_Test_File_Already_Exists() throws IOException {

        Path fullFilePath = tempDirectory.resolve("testFile1.txt");

        Boolean fileNotExists = Files.notExists(fullFilePath);
        sftA.assertTrue(fileNotExists, "_3_Negative_Test: File '" + fullFilePath.toString() + "' already exists!");

        if (fileNotExists) {
            tempFile =  Files.createFile(fullFilePath);
        } else {
            tempFile = fullFilePath;
        }

        System.out.println("_3_Negative_Test - finished");
    }

}
