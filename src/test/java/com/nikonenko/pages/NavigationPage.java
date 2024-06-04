package com.nikonenko.pages;

import com.nikonenko.util.LocatorsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage {
    private final WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clickOnNavigateButton() {
        driver.findElement(By.id(LocatorsUtil.OPEN_MODAL_BUTTON_ID)).click();
    }

    public void clickOnCloseNavigateButton(WebDriverWait wait) {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(LocatorsUtil.CLOSE_MODAL_BUTTON_ID))).click();
    }

    public void clickOnAboutButton(WebDriverWait wait) {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LocatorsUtil.ABOUT_XPATH))).click();
    }

    public void clickOnAllItemsButton(WebDriverWait wait) {
        clickOnNavigateButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LocatorsUtil.ALL_ITEMS_XPATH))).click();
    }

    public LoginPage clickOnLogoutButton(WebDriverWait wait) {
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
}
