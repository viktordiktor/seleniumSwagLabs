package com.nikanenka.pages;

import com.nikanenka.util.LocatorsUtil;
import org.junit.jupiter.api.Assertions;
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

    public CheckoutPage clickOnCheckoutButton() {
        driver.findElement(By.cssSelector("#checkout")).click();
        return new CheckoutPage(driver);
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

    private boolean isCartContainsRightAmountOfItems(int amount) {
        return amount == getCartItemsAmount();
    }

    public void assertThatCartContainsAllItems() {
        Assertions.assertTrue(isCartContainsRightAmountOfItems(6));
    }

    public void assertThatCartIsEmpty() {
        Assertions.assertTrue(isCartContainsRightAmountOfItems(0));
    }
}
