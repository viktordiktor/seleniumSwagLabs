package com.nikonenko.pages;

import com.nikonenko.util.DataUtil;
import com.nikonenko.util.LocatorsUtil;
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

    public boolean isCartHasCountWidget() {
        return driver.findElements(By.xpath(LocatorsUtil.CART_LINK_CHILD_XPATH)).size() > 0;
    }

    public int getCountWidgetValue() {
        WebElement countWidget = driver.findElement(By.xpath(LocatorsUtil.CART_WIDGET_XPATH));
        return Integer.parseInt(countWidget.getText());
    }

    public ItemPage getItemByImgHref(int id) {
        driver.findElement(By.id(String.format(LocatorsUtil.ITEM_IMAGE_HREF_ID, id))).click();
        return new ItemPage(driver);
    }
}
