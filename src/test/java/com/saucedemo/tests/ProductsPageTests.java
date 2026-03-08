package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.dataproviders.TestDataProvider;
import com.saucedemo.driver.DriverManager;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.reports.ReportLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.saucedemo.utils.StringUtil.splitByCharacterToList;
import static org.testng.Assert.*;

public class ProductsPageTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(ProductsPageTests.class);

    @Test(dataProvider = "addProductsToCartCSVTestData", dataProviderClass = TestDataProvider.class)
    public void addProductsToCart(Map<String, String> data) {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());
        List<String> products = splitByCharacterToList(data.get("products"),'|');
        int numberOfProducts = products.size();

        log.info("Login in to the website");
        ReportLogger.infoBold("<b>Login in to the website</b>");
        loginPage.login(data.get("username"),data.get("password"));

        log.info("Adding products "+numberOfProducts+" into the cart");
        ReportLogger.infoBold("Adding "+numberOfProducts+" products into the cart");
        for (String product : products) {
            productsPage.addItemToCartByName(product);
        }

        log.info("Verify the number of products added into the cart");
        ReportLogger.infoBold("Verify the number of products added into the cart");
        int cartCount = Integer.parseInt(productsPage.getCartCount());
        assertEquals(numberOfProducts,cartCount);

    }

    @Test(dataProvider = "removeProductsFromCartCSVTestData", dataProviderClass = TestDataProvider.class)
    public void removeProductsFromCart(Map<String, String> data) {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());
        List<String> products = splitByCharacterToList(data.get("products"),'|');
        int numberOfProducts = products.size();
        List<String> productsToRemove = splitByCharacterToList(data.get("removeProducts"),'|');
        int numberOfProductsToRemove = productsToRemove.size();

        log.info("Login in to the website");
        ReportLogger.infoBold("Login in to the website");
        loginPage.login(data.get("username"),data.get("password"));

        log.info("Adding products "+numberOfProducts+" into the cart");
        ReportLogger.infoBold("Adding "+numberOfProducts+" products into the cart");
        for (String product : products) {
            productsPage.addItemToCartByName(product);
        }

        log.info("Removing products "+numberOfProductsToRemove+" from the cart");
        ReportLogger.infoBold("Removing "+numberOfProductsToRemove+" products from the cart");
        for (String productToRemove : productsToRemove) {
            productsPage.addItemToCartByName(productToRemove);
        }


        log.info("Verify the number of products in the cart");
        ReportLogger.infoBold("Verify the number of products in the cart");
        int cartCount = Integer.parseInt(productsPage.getCartCount());
        int expectedNumberInTheCart = numberOfProducts - numberOfProductsToRemove;

        assertEquals(cartCount,expectedNumberInTheCart);

    }

}
