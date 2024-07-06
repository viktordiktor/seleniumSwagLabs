package com.nikonenko.tests;

import com.nikonenko.pages.CartPage;
import com.nikonenko.pages.CheckoutPage;
import com.nikonenko.pages.HomePage;
import com.nikonenko.pages.LoginPage;
import com.nikonenko.pages.NavigationPage;
import com.nikonenko.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class CheckoutTests {
    private static WebDriver driver;
    private CheckoutPage checkoutPage;

    @BeforeEach
    public void setup() {
        driver = TestUtil.getConfigureChromeDriver();
        WebDriverManager.chromedriver().setup();

        checkoutPage = new LoginPage(driver)
                .goToHomePage()
                .clickOnCartButton()
                .clickOnCheckoutButton();
    }

    @Test
    @DisplayName("Check that checkout with empty fields leads to error")
    public void checkThatCheckoutWithEmptyFieldsLeadsToError() {
        checkoutPage.clickOnContinueButton();
        checkoutPage.assertThatPageContainsCheckoutError();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}