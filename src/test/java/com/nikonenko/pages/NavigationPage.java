package com.nikonenko.pages;

import com.nikonenko.util.FailUtil;
import com.nikonenko.util.LocatorsUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clickOnNavigateButton() {
        driver.findElement(By.id(LocatorsUtil.OPEN_MODAL_BUTTON_ID)).click();
    }

    public void clickOnCloseNavigateButton() {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(LocatorsUtil.CLOSE_MODAL_BUTTON_ID))).click();
    }

    public void clickOnAboutButton() {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LocatorsUtil.ABOUT_XPATH))).click();
    }

    public void clickOnAllItemsButton() {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LocatorsUtil.ALL_ITEMS_XPATH))).click();
    }

    public LoginPage clickOnLogoutButton() {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LocatorsUtil.LOGOUT_CSS))).click();
        return new LoginPage(driver);
    }

    public void clickOnTwitterButton() {
        driver.findElement(By.xpath(LocatorsUtil.TWITTER_XPATH)).click();
    }

    public void clickOnFacebookButton() {
        driver.findElement(By.xpath(LocatorsUtil.FACEBOOK_XPATH)).click();
    }

    public void clickOnLinkedinButton() {
        driver.findElement(By.xpath(LocatorsUtil.LINKEDIN_XPATH)).click();
    }

    public CartPage clickOnCartButton() {
        driver.findElement(By.xpath(LocatorsUtil.CART_XPATH)).click();
        return new CartPage(driver);
    }

    public boolean checkIfModalActive() {
        return driver.findElement(By.xpath(LocatorsUtil.MODAL_XPATH)).isDisplayed();
    }

    public void assertRedirectToPage(String url) {
        Assertions.assertEquals(url, getUrl());
    }

    public void assertModalActive() {
        Assertions.assertTrue(checkIfModalActive());
    }

    public void assertModalNotActive() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LocatorsUtil.MODAL_XPATH)));
        Assertions.assertFalse(checkIfModalActive());
    }

    public void assertRedirectToPageInNewWindow(String expectedUrl) {
        driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(driver.getWindowHandle()))
                .findFirst()
                .ifPresentOrElse(
                        handle -> {
                            driver.switchTo().window(handle);
                            Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
                        },
                        () -> Assertions.fail(String.format(FailUtil.NEW_WINDOW_FAIL, expectedUrl))
                );
    }
}
