package com.nikonenko.tests;

import com.nikonenko.pages.LoginPage;
import com.nikonenko.pages.NavigationPage;
import com.nikonenko.util.FailUtil;
import com.nikonenko.util.LocatorsUtil;
import com.nikonenko.util.TestUtil;
import com.nikonenko.util.UrlUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationTests {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private NavigationPage navigationPage;

    @BeforeEach
    public void setup() {
        driver = TestUtil.getConfigureChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverManager.chromedriver().setup();

        LoginPage loginPage = new LoginPage(driver);
        navigationPage = loginPage.goToHomePage();
    }

    @Nested
    class ModalActions {
        @Test
        @DisplayName("Check that modal window is Opened and Active")
        public void checkModalWindowIsOpenedAndActive() {
            navigationPage.clickOnNavigateButton();
            Assertions.assertTrue(navigationPage.checkIfModalActive());
        }

        @Test
        @DisplayName("Check that modal window Closed and not Active")
        public void checkModalWindowClosedAndNotActive() {
            navigationPage.clickOnCloseNavigateButton(wait);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LocatorsUtil.MODAL_XPATH)));
            Assertions.assertFalse(navigationPage.checkIfModalActive());
        }

        @Test
        @DisplayName("Check that About button redirect to About Page")
        public void checkAboutButtonRedirectToAboutPage() {
            navigationPage.clickOnAboutButton(wait);
            Assertions.assertEquals(UrlUtil.ABOUT_URL, navigationPage.getUrl());
        }

        @Test
        @DisplayName("Check that logout redirect to login page and clear User Session")
        public void checkLogoutRedirectToLoginPageAndClearUserSession() {
            Assertions.assertFalse(navigationPage.clickOnLogoutButton(wait).isSessionUsernameExists());
            Assertions.assertEquals(UrlUtil.ROOT_URL, navigationPage.getUrl());
        }

        @Test
        @DisplayName("Check that All Items button redirect to Inventory Page")
        public void checkAllItemsButtonRedirectToInventoryPage() {
            navigationPage.clickOnAllItemsButton(wait);
            Assertions.assertEquals(UrlUtil.INVENTORY_PAGE, navigationPage.getUrl());
        }
    }

    @Nested
    class SocialNetworksActions {
        @Test
        @DisplayName("Check that Twitter button redirects to Twitter page in a new window")
        @Flaky
        public void checkTwitterButtonRedirectsToTwitterPageInNewWindow() {
            navigationPage.clickOnTwitterButton();

            checkButtonRedirectsToPageInNewWindow(UrlUtil.TWITTER_URL);
        }

        @Test
        @DisplayName("Check that Facebook button redirects to Facebook page in a new window")
        public void checkFacebookButtonRedirectToFacebookPageInNewWindow() {
            navigationPage.clickOnFacebookButton();

            checkButtonRedirectsToPageInNewWindow(UrlUtil.FACEBOOK_URL);
        }

        @Test
        @DisplayName("Check that Linkedin button redirects to Linkedin page in a new window")
        public void checkFacebookButtonRedirectToLinkedinPageInNewWindow() {
            navigationPage.clickOnLinkedinButton();

            checkButtonRedirectsToPageInNewWindow(UrlUtil.LINKEDIN_URL);
        }

        private void checkButtonRedirectsToPageInNewWindow(String expectedUrl) {
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

    @Test
    @DisplayName("Check that Cart button redirects to Cart")
    public void checkThatCartButtonRedirectsToCart() {
        Assertions.assertEquals(UrlUtil.CART_PAGE, navigationPage.clickOnCartButton().getUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
