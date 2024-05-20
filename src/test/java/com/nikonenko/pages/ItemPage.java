package com.nikonenko.pages;

import org.openqa.selenium.WebDriver;

public class ItemPage extends NavigationPage {
    private final WebDriver driver;

    public ItemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
