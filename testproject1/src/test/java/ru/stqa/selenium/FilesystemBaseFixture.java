package ru.stqa.selenium;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Test
public class FilesystemBaseFixture {

    public Path tempDirectory = null;

    @BeforeClass
    public void baseFSSetup() throws IOException{

        System.out.println("baseFSSetup");

        Path rootDirectory = Paths.get(".\\TempDir");
        tempDirectory = Files.createDirectory(rootDirectory);

    }

    @AfterClass
    public void baseFSTeardown() throws IOException {

        System.out.println("baseFSTeardown");

        Files.deleteIfExists(tempDirectory);
        tempDirectory = null;
    }

}
