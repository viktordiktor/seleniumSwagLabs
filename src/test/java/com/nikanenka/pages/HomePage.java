package com.nikanenka.pages;

import com.nikanenka.util.DataUtil;
import com.nikanenka.util.LocatorsUtil;
import com.nikanenka.util.SortUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends NavigationPage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void sortByNameAsc() {
        getSelectDropDown().selectByValue(DataUtil.NAME_ASC);
    }

    public void sortByNameDesc() {
        getSelectDropDown().selectByValue(DataUtil.NAME_DESC);
    }

    public void sortByPriceAsc() {
        getSelectDropDown().selectByValue(DataUtil.PRICE_ASC);
    }

    public void sortByPriceDesc() {
        getSelectDropDown().selectByValue(DataUtil.PRICE_DESC);
    }

    public Select getSelectDropDown() {
        return new Select(driver.findElement(By.xpath(LocatorsUtil.SELECT_DROPDOWN_XPATH)));
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(By.xpath(LocatorsUtil.ITEM_NAMES_XPATH));
    }

    public List<WebElement> getItemPrices() {
        return driver.findElements(By.xpath(LocatorsUtil.ITEM_PRICES_XPATH));
    }

    public List<WebElement> getAddToCartButtons() {
        return driver.findElements(By.xpath(LocatorsUtil.ADD_TO_CART_XPATH));
    }

    public List<WebElement> getRemoveFromCartButtons() {
        return driver.findElements(By.xpath(LocatorsUtil.REMOVE_FROM_CART_MAIN_XPATH));
    }

    public void addAllItemsToCart() {
        getAddToCartButtons().forEach(WebElement::click);
    }

    public void removeAllItemsFromCart() {
        getRemoveFromCartButtons().forEach(WebElement::click);
    }

    private boolean isCartHasCountWidget() {
        return driver.findElements(By.xpath(LocatorsUtil.CART_LINK_CHILD_XPATH)).size() > 0;
    }

    public void assertThatCartHasCountWidget() {
        Assertions.assertTrue(isCartHasCountWidget());
    }

    public void assertThatCartHasNotCountWidget() {
        Assertions.assertFalse(isCartHasCountWidget());
    }

    private int getCountWidgetValue() {
        WebElement countWidget = driver.findElement(By.xpath(LocatorsUtil.CART_WIDGET_XPATH));
        return Integer.parseInt(countWidget.getText());
    }

    public void assertThatCountWidgetContainsAllItems() {
        Assertions.assertEquals(6, getCountWidgetValue());
    }

    public ItemPage getItemByImgHref(int id) {
        driver.findElement(By.id(String.format(LocatorsUtil.ITEM_IMAGE_HREF_ID, id))).click();
        return new ItemPage(driver);
    }

    public void assertAscNameSort() {
        Assertions.assertTrue(SortUtils.checkCorrectNameAsc(getItemNames()));
    }

    public void assertDescNameSort() {
        Assertions.assertTrue(SortUtils.checkCorrectNameDesc(getItemNames()));
    }

    public void assertAscPriceSort() {
        Assertions.assertTrue(SortUtils.checkCorrectPriceAsc(getItemPrices()));
    }

    public void assertDescPriceSort() {
        Assertions.assertTrue(SortUtils.checkCorrectPriceDesc(getItemPrices()));
    }

    private void assertThatListContainsAvailableButtons(List<WebElement> elementList) {
        Assertions.assertEquals(6, elementList.size());
        for (WebElement element : elementList) {
            Assertions.assertTrue(element.isDisplayed());
            Assertions.assertTrue(element.isEnabled());
        }
    }

    public void assertAddToCartButtonsAvailable() {
        Assertions.assertTrue(getRemoveFromCartButtons().isEmpty());
        assertThatListContainsAvailableButtons(getAddToCartButtons());
    }

    public void assertRemoveFromCartButtonsNotAvailable() {
        Assertions.assertTrue(getAddToCartButtons().isEmpty());
        assertThatListContainsAvailableButtons(getRemoveFromCartButtons());
    }
}
