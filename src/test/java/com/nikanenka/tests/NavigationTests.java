package com.nikanenka.tests;

import com.nikanenka.drivers.DriverFactory;
import com.nikanenka.pages.LoginPage;
import com.nikanenka.pages.NavigationPage;
import com.nikanenka.util.UrlUtil;
import io.qameta.allure.Flaky;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class NavigationTests {
    private static WebDriver driver;
    private NavigationPage navigationPage;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getConfiguredDriver();

        LoginPage loginPage = new LoginPage(driver);
        navigationPage = loginPage.goToHomePage();
    }

    @Nested
    class ModalActions {
        @Test
        @DisplayName("Check that modal window is Opened and Active")
        public void checkModalWindowIsOpenedAndActive() {
            navigationPage.clickOnNavigateButton();
            navigationPage.assertModalActive();
        }

        @Test
        @DisplayName("Check that modal window Closed and not Active")
        public void checkModalWindowClosedAndNotActive() {
            navigationPage.clickOnCloseNavigateButton();
            navigationPage.assertModalNotActive();
        }

        @Test
        @DisplayName("Check that About button redirect to About page")
        public void checkAboutButtonRedirectToAboutPage() {
            navigationPage.clickOnAboutButton();
            navigationPage.assertRedirectToPage(UrlUtil.ABOUT_URL);
        }

        @Test
        @DisplayName("Check that logout redirect to login page and clear User Session")
        public void checkLogoutRedirectToLoginPageAndClearUserSession() {
            navigationPage.clickOnLogoutButton()
                    .assertThatSessionUsernameCookiesAreNotExists();
            navigationPage.assertRedirectToPage(UrlUtil.ROOT_URL);
        }

        @Test
        @DisplayName("Check that All Items button redirect to Inventory page")
        public void checkAllItemsButtonRedirectToInventoryPage() {
            navigationPage.clickOnAllItemsButton();
            navigationPage.assertRedirectToPage(UrlUtil.INVENTORY_PAGE);
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

        @Step("Check Button redirects to Page in new window")
        private void checkButtonRedirectsToPageInNewWindow(String expectedUrl) {
            navigationPage.assertRedirectToPageInNewWindow(expectedUrl);
        }
    }

    @Test
    @DisplayName("Check that Cart button redirects to Cart")
    public void checkThatCartButtonRedirectsToCart() {
        navigationPage.clickOnCartButton();
        navigationPage.assertRedirectToPage(UrlUtil.CART_PAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
