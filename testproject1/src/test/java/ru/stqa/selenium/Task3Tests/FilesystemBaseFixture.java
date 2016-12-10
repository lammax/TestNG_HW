package ru.stqa.selenium.Task3Tests;

//import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
//import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesystemBaseFixture {

    public Path tempDirectory = null;

    @BeforeClass(alwaysRun = true)
    public void baseFSSetup() throws IOException{

        System.out.println("@BeforeClass baseFSSetup");

        Path rootDirectory = Paths.get(".\\TempDir");
        tempDirectory = Files.createDirectory(rootDirectory);

    }

    @AfterClass(alwaysRun = true)
    public void baseFSTeardown() throws IOException {

        System.out.println("@AfterClass baseFSTeardown");

        Files.deleteIfExists(tempDirectory);
        tempDirectory = null;
    }

}