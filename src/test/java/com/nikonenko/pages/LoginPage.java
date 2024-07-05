package com.nikonenko.pages;

import com.nikonenko.util.DataUtil;
import com.nikonenko.util.UrlUtil;
import com.nikonenko.util.LocatorsUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        driver.get(UrlUtil.ROOT_URL);
        this.driver = driver;
    }

    public void loginToPage(String username, String password) {
        driver
                .findElement(By.xpath(LocatorsUtil.LOGIN_USERNAME_XPATH))
                .sendKeys(username, Keys.TAB, password, Keys.ENTER);
    }

    public HomePage goToHomePage() {
        loginToPage(DataUtil.STANDARD_USER, DataUtil.DEFAULT_PASSWORD);
        return new HomePage(driver);
    }

    public HomePage goToProblemHomePage() {
        loginToPage(DataUtil.PROBLEM_USER, DataUtil.DEFAULT_PASSWORD);
        return new HomePage(driver);
    }

    public void sendInvalidUsername() {
        loginToPage(DataUtil.INVALID_USER, DataUtil.DEFAULT_PASSWORD);
    }

    public void sendInvalidPassword() {
        loginToPage(DataUtil.STANDARD_USER, DataUtil.INVALID_PASSWORD);
    }

    public boolean isCredentialsValid() {
        return driver
                .findElements(By.xpath(LocatorsUtil.LOGIN_ERROR_XPATH))
                .isEmpty();
    }

    public boolean isSessionUsernameCookiesCorrect(String username) {
        return driver.manage().getCookies()
                .stream()
                .anyMatch(cookie ->
                        cookie.getName().equals(DataUtil.SESSION_USERNAME_KEY)
                            && cookie.getValue().equals(username));
    }

    public boolean isSessionUsernameExists() {
        return driver.manage().getCookies()
                .stream()
                .anyMatch(cookie ->
                        cookie.getName().equals(DataUtil.SESSION_USERNAME_KEY));
    }

    public void assertRedirectToHomePage() {
        Assertions.assertEquals(UrlUtil.INVENTORY_PAGE, driver.getCurrentUrl());
    }

    public void assertThatCookiesContainsUsername(String username) {
        Assertions.assertTrue(isSessionUsernameCookiesCorrect(username));
    }

    public void assertThatCredentialsAreNotValid() {
        Assertions.assertFalse(isCredentialsValid());
    }

    public void assertThatSessionUsernameCookiesAreNotExists() {
        Assertions.assertFalse(isSessionUsernameExists());
    }
}
