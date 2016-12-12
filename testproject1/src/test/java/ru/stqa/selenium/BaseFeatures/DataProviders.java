package ru.stqa.selenium.BaseFeatures;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> fileNames() {

        List<Object[]> data = new ArrayList<Object[]>();

        for(int i = 0; i < 3; i++) {
            data.add(new Object[]{
                generateRandomFilename()
            });
        }

        return data.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loadFileNames() throws IOException {

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

        return data.iterator();
    }

    private static String generateRandomFilename() {
        return "file" + (new Random()).nextInt() + ".txt";
    }

}
