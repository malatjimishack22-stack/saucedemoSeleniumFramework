package com.saucedemo.dataproviders;

import com.saucedemo.data.reader.CsvDataReader;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    //CSV
    @DataProvider(name = "loginCSVTestData", parallel = true)
    public static Object[][] loginCSVTestData() {
        return CsvDataReader.read("src/test/resources/testdata/csv/login.csv");
    }
}
