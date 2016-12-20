package ru.stqa.selenium.BaseFeatures;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniversalDataProvider {

    @DataProvider
    public static Object[][] dataSourceLoader(FrameworkMethod testMethod) throws IOException {

        DataSource ds = testMethod.getAnnotation(DataSource.class);

        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
        }
        switch (ds.type()) {
            case AUTO:
                return fileNames();

            case FILE:
                return loadFileNames(ds.value());

            default:
                throw new Error("Data source type is not supported: " + ds.type());
        }
    }


    private static Object[][] fileNames() {

        List<Object[]> data = new ArrayList<Object[]>();

        for(int i = 0; i < 3; i++) {
            data.add(new Object[]{
                    UniversalDataProvider.generateRandomFilename()
            });
        }

        return data.toArray(new Object[][]{});
    }

    private static Object[][] loadFileNames(String filepath) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        UniversalDataProvider.class.getResourceAsStream(filepath)
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
