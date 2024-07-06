package com.nikonenko.tests;

import com.nikonenko.pages.CartPage;
import com.nikonenko.pages.HomePage;
import com.nikonenko.pages.LoginPage;
import com.nikonenko.util.TestUtil;
import com.nikonenko.util.UrlUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
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
        driver = TestUtil.getConfigureChromeDriver();
        WebDriverManager.chromedriver().setup();

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
