package com.nikonenko.pages;

import com.nikonenko.util.LocatorsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends NavigationPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage clickOnContinueShoppingButton() {
        driver.findElement(By.id(LocatorsUtil.CONTINUE_SHOPPING_ID)).click();
        return new HomePage(driver);
    }

    public int getCartItemsAmount() {
        return driver.findElements(By.xpath("//div[@class='cart_item']")).size();
    }

    public List<WebElement> getRemoveFromCartButtons() {
        return driver.findElements(By.xpath(LocatorsUtil.REMOVE_FROM_CART_XPATH));
    }

    public void removeAllItemsFromCart() {
        getRemoveFromCartButtons().forEach(WebElement::click);
    }
}
