package com.nikonenko.tests;

import com.nikonenko.pages.HomePage;
import com.nikonenko.pages.LoginPage;
import com.nikonenko.util.SortUtils;
import com.nikonenko.util.TestUtil;
import com.nikonenko.util.UrlUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
        driver = TestUtil.getConfigureChromeDriver();
        WebDriverManager.chromedriver().setup();

        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
    }

    @Nested
    class SortActions {
        @Test
        @DisplayName("Check that sortNameAsc working correctly")
        public void checkThatSortNameAscWorkingCorrectly() {
            homePage.sortByNameAsc();
            Assertions.assertTrue(SortUtils.checkCorrectNameAsc(homePage.getItemNames()));
        }

        @Test
        @DisplayName("Check that sortNameDesc working correctly")
        public void checkThatSortNameDescWorkingCorrectly() {
            homePage.sortByNameDesc();
            Assertions.assertTrue(SortUtils.checkCorrectNameDesc(homePage.getItemNames()));
        }

        @Test
        @DisplayName("Check that sortPriceAsc working correctly")
        public void checkThatSortPriceAscWorkingCorrectly() {
            homePage.sortByPriceAsc();
            Assertions.assertTrue(SortUtils.checkCorrectPriceAsc(homePage.getItemPrices()));
        }

        @Test
        @DisplayName("Check that sortPriceDesc working correctly")
        public void checkThatSortPriceDescWorkingCorrectly() {
            homePage.sortByPriceDesc();
            Assertions.assertTrue(SortUtils.checkCorrectPriceDesc(homePage.getItemPrices()));
        }
    }

    @ParameterizedTest
    @DisplayName("Check that img href redirects to correct Item page")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void checkThatImgHrefRedirectsToCorrectItemPage(int id) {
        Assertions.assertEquals(String.format(UrlUtil.ITEM_URL, id), homePage.getItemByImgHref(id).getUrl());
    }

    @Nested
    class CartActions {
        @Step("Check that all 'Add to cart' buttons available")
        public void checkThatAllAddToCartButtonsAvailable() {
            Assertions.assertTrue(homePage.getRemoveFromCartButtons().isEmpty());
            Assertions.assertEquals(6, homePage.getAddToCartButtons().size());
        }

        @Step("Check that all 'Remove from cart' buttons available")
        public void checkThatAllRemoveFromCartButtonsAvailable() {
            Assertions.assertTrue(homePage.getAddToCartButtons().isEmpty());
            Assertions.assertEquals(6, homePage.getRemoveFromCartButtons().size());
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

        @Test
        @DisplayName("Check Cart count widget")
        public void checkCartCountWidget() {
            Assertions.assertFalse(homePage.isCartHasCountWidget());

            homePage.addAllItemsToCart();
            Assertions.assertEquals(6, homePage.getCountWidgetValue());

            homePage.removeAllItemsFromCart();
            Assertions.assertFalse(homePage.isCartHasCountWidget());
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
