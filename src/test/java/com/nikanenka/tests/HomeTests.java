package com.nikanenka.tests;

import com.nikanenka.drivers.DriverFactory;
import com.nikanenka.pages.HomePage;
import com.nikanenka.pages.LoginPage;
import com.nikanenka.util.UrlUtil;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

public class HomeTests {
    private static WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getConfiguredDriver();

        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
    }

    @Nested
    class SortActions {
        @Test
        @DisplayName("Check that sortNameAsc working correctly")
        public void checkThatSortNameAscWorkingCorrectly() {
            homePage.sortByNameAsc();
            homePage.assertAscNameSort();
        }

        @Test
        @DisplayName("Check that sortNameDesc working correctly")
        public void checkThatSortNameDescWorkingCorrectly() {
            homePage.sortByNameDesc();
            homePage.assertDescNameSort();
        }

        @Test
        @DisplayName("Check that sortPriceAsc working correctly")
        public void checkThatSortPriceAscWorkingCorrectly() {
            homePage.sortByPriceAsc();
            homePage.assertAscPriceSort();
        }

        @Test
        @DisplayName("Check that sortPriceDesc working correctly")
        public void checkThatSortPriceDescWorkingCorrectly() {
            homePage.sortByPriceDesc();
            homePage.assertDescPriceSort();
        }
    }

    @ParameterizedTest
    @DisplayName("Check that img href redirects to correct Item page")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void checkThatImgHrefRedirectsToCorrectItemPage(int id) {
        homePage.getItemByImgHref(id);
        homePage.assertRedirectToPage(String.format(UrlUtil.ITEM_URL, id));
    }

    @Nested
    class CartActions {
        @Step("Check that all 'Add to cart' buttons available")
        public void checkThatAllAddToCartButtonsAvailable() {
            homePage.assertAddToCartButtonsAvailable();
        }

        @Step("Check that all 'Remove from cart' buttons available")
        public void checkThatAllRemoveFromCartButtonsAvailable() {
            homePage.assertRemoveFromCartButtonsNotAvailable();
        }

        @Test
        @DisplayName("Check 'Add to Cart' button changes to 'Remove' button")
        public void checkAddToCartButtonChangesToRemoveButton() {
            checkThatAllAddToCartButtonsAvailable();

            homePage.addAllItemsToCart();

            checkThatAllRemoveFromCartButtonsAvailable();
        }

        @Test
        @DisplayName("Check 'Remove from Cart' button changes to 'Add' button")
        public void checkRemoveFromCartButtonChangesToAddButton() {
            homePage.addAllItemsToCart();
            homePage.removeAllItemsFromCart();

            checkThatAllAddToCartButtonsAvailable();
        }

        @Step("Add all Items to Cart and check that Count Widget contains all Items")
        private void addAllItemsToCartAndCheckThatCountWidgetContainsAllItems() {
            homePage.addAllItemsToCart();
            homePage.assertThatCountWidgetContainsAllItems();
        }

        @Step("Remove all Items from Cart and check that Count Widget contains no Items")
        private void removeAllItemsFromCartAndCheckThatCountWidgetContainsNoItems() {
            homePage.removeAllItemsFromCart();
            homePage.assertThatCartHasNotCountWidget();
        }

        @Test
        @DisplayName("Check Cart count widget")
        public void checkCartCountWidget() {
            homePage.assertThatCartHasNotCountWidget();

            addAllItemsToCartAndCheckThatCountWidgetContainsAllItems();

            removeAllItemsFromCartAndCheckThatCountWidgetContainsNoItems();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
