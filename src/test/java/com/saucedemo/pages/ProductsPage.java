package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.elements.Element;
import com.saucedemo.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final Element cartBadge = new Element("Cart Badge", By.className("shopping_cart_badge"));
    private final Element cartLink = new Element("Cart Link", By.className("shopping_cart_link"));

    private Element itemByName(String name){
        return new Element(name, By.xpath("//div[text()='"+ StringUtil.toTitleCase(name) +"']/ancestor::div[@class='inventory_item']//button"));
    }

    private Element itemPriceByName(String name){
        return new Element(name, By.xpath("//div[text()='"+ StringUtil.toTitleCase(name) +"']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"));
    }

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public void addItemToCartByName(String name){
        click(itemByName(name));
    }

    public String getItemPrice(String name){
        return getText(itemPriceByName(name));
    }

    public String getCartCount(){
        if (getElementCount(cartBadge) == 0) { //When cart count is not available
            return "0";
        } else {
            return getText(cartBadge);
        }
    }

    public void goToCart(){
        click(cartLink);
    }
}
