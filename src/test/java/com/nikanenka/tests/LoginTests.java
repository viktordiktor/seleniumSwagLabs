package com.nikanenka.tests;

import com.nikanenka.drivers.DriverFactory;
import com.nikanenka.pages.LoginPage;
import com.nikanenka.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LoginTests {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getConfiguredDriver();

        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Check that the login to home page works and cookies correct")
    public void checkThatTheLoginToHomePageWorks() {
        loginPage.goToHomePage();
        loginPage.assertRedirectToHomePage();
        loginPage.assertThatCookiesContainsUsername(DataUtil.STANDARD_USER);
    }

    @Test
    @DisplayName("Check that login to problem user returns a home page")
    void checkThatLoginToProblemUserReturnsAHomePage() {
        loginPage.goToProblemHomePage();
        loginPage.assertRedirectToHomePage();
        loginPage.assertThatCookiesContainsUsername(DataUtil.PROBLEM_USER);
    }

    @Test
    @DisplayName("Check that login with invalid Username returns an error")
    void checkThatLoginWithInvalidUsernameReturnsAnError(){
        loginPage.sendInvalidUsername();
        loginPage.assertThatCredentialsAreNotValid();
        loginPage.assertThatSessionUsernameCookiesAreNotExists();
    }

    @Test
    @DisplayName("Check that login with invalid Password returns an error")
    void checkThatLoginWithInvalidPasswordReturnsAnError(){
        loginPage.sendInvalidPassword();
        loginPage.assertThatCredentialsAreNotValid();
        loginPage.assertThatSessionUsernameCookiesAreNotExists();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
