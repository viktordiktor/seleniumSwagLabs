package com.nikonenko.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends NavigationPage {
    private final WebDriver webDriver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.webDriver = driver;
    }

    public void clickOnContinueButton() {
        webDriver.findElement(By.cssSelector("[data-test='continue']")).click();
    }

    public void assertThatPageContainsCheckoutError() {
        Assertions.assertFalse(webDriver.findElements(By.xpath("//div[@class='error-message-container error']")).isEmpty());
    }
}
