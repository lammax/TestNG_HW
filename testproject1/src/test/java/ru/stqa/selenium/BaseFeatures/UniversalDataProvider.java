package ru.stqa.selenium.BaseFeatures;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class UniversalDataProvider {

    @DataProvider
    public static Iterator<Object[]> dataSourceLoader(Method m) throws IOException, InvalidFormatException, URISyntaxException {

        if (m.isAnnotationPresent(DataSource.class)) {

            DataSource ds = m.getAnnotation(DataSource.class);
            switch (ds.type()) {
                case AUTO:
                    return fileNames();

                case FILE:
                    return loadFileNames(ds.value());

                case EXCEL:
                    return loadEXLSXFileNames(ds.value());

                default:
                    throw new Error("Data source type is not supported: " + ds.type());
            }

        } else {
            throw new Error("There is no @DataSource annotation on method " + m);
        }



    }

    private static Iterator<Object[]> fileNames() {

        List<Object[]> data = new ArrayList<Object[]>();

        for(int i = 0; i < 3; i++) {
            data.add(new Object[]{
                    UniversalDataProvider.generateRandomFilename()
            });
        }

        return data.iterator();
    }

    private static Iterator<Object[]> loadFileNames(String filepath) throws IOException {

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

        return data.iterator();
    }

    private static Iterator<Object[]> loadEXLSXFileNames(String filepath) throws IOException, InvalidFormatException, URISyntaxException {

        List<Object[]> data = new ArrayList<>();

        File inputf = Paths.get(UniversalDataProvider.class.getResource(filepath).toURI()).toFile();
        InputStream inp = new FileInputStream(inputf);

        Workbook wb = null;

        try {

            wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);

            for (int r = 0; r < 10; r++) {
                data.add(new Object[]{
                        sheet.getRow(r).getCell(0).getStringCellValue()
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (wb != null) {
                wb.close();
            }

        }

        return data.iterator();
    }


    private static String generateRandomFilename() {
        return "file" + (new Random()).nextInt() + ".txt";
    }

}
