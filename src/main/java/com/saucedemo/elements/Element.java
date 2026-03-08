package com.saucedemo.elements;

import org.openqa.selenium.By;

public class Element {

    private final String name;
    private final By locator;

    public Element(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    public String getName() {
        return name;
    }

    public By getLocator() {
        return locator;
    }

}