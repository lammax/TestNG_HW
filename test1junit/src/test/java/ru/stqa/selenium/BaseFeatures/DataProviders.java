package ru.stqa.selenium.BaseFeatures;

import com.tngtech.java.junit.dataprovider.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataProviders {

    @DataProvider
    public static Object[][] fileNames() {

        List<Object[]> data = new ArrayList<Object[]>();

        for(int i = 0; i < 3; i++) {
            data.add(new Object[]{
                    DataProviders.generateRandomFilename()
            });
        }

        return data.toArray(new Object[][]{});
    }

    @DataProvider
    public static Object[][] loadFileNames() throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        DataProviders.class.getResourceAsStream("/Data/dataFileNames.txt")
                )
        );

        List<Object[]> data = new ArrayList<Object[]>();
        String line = in.readLine();

        while(line != null) {
            data.add(new Object[]{line});
            line = in.readLine();
        }

        in.close();

        return data.toArray(new Object[][]{});
    }

    private static String generateRandomFilename() {
        return "file" + (new Random()).nextInt() + ".txt";
    }

}