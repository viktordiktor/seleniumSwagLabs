package com.nikanenka.tests;

import com.nikanenka.drivers.DriverFactory;
import com.nikanenka.pages.CartPage;
import com.nikanenka.pages.HomePage;
import com.nikanenka.pages.LoginPage;
import com.nikanenka.util.UrlUtil;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class CartTests {
    private static WebDriver driver;
    private CartPage cartPage;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getConfiguredDriver();

        cartPage = new LoginPage(driver)
                .goToHomePage()
                .clickOnCartButton();
    }

    @Test
    @DisplayName("Check that 'Continue Shopping' button redirect to Inventory Page")
    public void checkThatContinueShoppingButtonRedirectToInventoryPage() {
        HomePage homePage = cartPage.clickOnContinueShoppingButton();
        homePage.assertRedirectToPage(UrlUtil.INVENTORY_PAGE);
    }

    @Nested
    class ItemsActions {
        @Test
        @DisplayName("Check that Cart contains all added Items")
        public void checkThatCartContainsAllAddedItems() {
            addAllItemsToCartFromHomePage();

            cartPage.assertThatCartContainsAllItems();
        }

        @Test
        @DisplayName("Check that Remove from Cart correctly removes Items")
        public void checkThatRemoveFromCartCorrectlyRemovesItems() {
            addAllItemsToCartFromHomePage();

            cartPage.removeAllItemsFromCart();

            cartPage.assertThatCartIsEmpty();
        }

        @Step("Add all Items to Cart from Home Page")
        private void addAllItemsToCartFromHomePage() {
            HomePage homePage = cartPage.clickOnContinueShoppingButton();
            homePage.addAllItemsToCart();
            homePage.clickOnCartButton();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
