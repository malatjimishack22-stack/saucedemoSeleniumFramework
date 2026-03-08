package com.saucedemo.dataproviders;

import com.saucedemo.data.reader.CsvDataReader;
import com.saucedemo.data.reader.JsonDataReader;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class TestDataProvider {

    //CSV
    @DataProvider(name = "loginCSVTestData", parallel = true)
    public static Object[][] loginCSVTestData() {
        return CsvDataReader.read("src/test/resources/testdata/csv/login.csv");
    }

    @DataProvider(name = "addProductsToCartCSVTestData", parallel = true)
    public static Object[][] addProductsToCartCSVTestData() {
        return CsvDataReader.read("src/test/resources/testdata/csv/addProductsToCart.csv");
    }

    @DataProvider(name = "removeProductsFromCartCSVTestData", parallel = true)
    public static Object[][] removeProductsFromCartCSVTestData() {
        return CsvDataReader.read("src/test/resources/testdata/csv/removeProductsFromCart.csv");
    }

    //Json
    @DataProvider(name = "loginJsonData", parallel = true)
    public static Object[][] loginJsonData() {
        List<Map<String, String>> rows = JsonDataReader.readAsMaps("src/test/resources/testdata/json/login.json");
        return JsonDataReader.toSingleColumnData(rows);
    }
}
