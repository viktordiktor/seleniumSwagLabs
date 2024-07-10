package com.nikanenka.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocatorsUtil {
    public static final String LOGIN_USERNAME_XPATH = "//div[@class='login-box']//form//div//input[@id='user-name']";
    public static final String LOGIN_ERROR_XPATH = "//div[@class='error-message-container error']//h3[@data-test='error']";
    public static final String SELECT_DROPDOWN_XPATH = "//span[@class='select_container']//select[@class='product_sort_container']";
    public static final String OPEN_MODAL_BUTTON_ID = "react-burger-menu-btn";
    public static final String CLOSE_MODAL_BUTTON_ID = "react-burger-cross-btn";
    public static final String MODAL_XPATH = "//div[@class='bm-menu-wrap']";
    public static final String ITEM_NAMES_XPATH = "//div[@class='inventory_item_name ']";
    public static final String ITEM_PRICES_XPATH = "//div[@class='inventory_item_price']";
    public static final String ABOUT_XPATH = "//a[@id='about_sidebar_link' and @class='bm-item menu-item']";
    public static final String ALL_ITEMS_XPATH = "//a[@id='inventory_sidebar_link' and @class='bm-item menu-item']";
    public static final String LOGOUT_CSS = "a#logout_sidebar_link.bm-item.menu-item";
    public static final String TWITTER_XPATH = "//li[@class='social_twitter']//a";
    public static final String FACEBOOK_XPATH = "//li[@class='social_facebook']//a";
    public static final String LINKEDIN_XPATH = "//li[@class='social_linkedin']//a";
    public static final String CART_XPATH = "//div[@id='shopping_cart_container']//a[@class='shopping_cart_link']";
    public static final String ADD_TO_CART_XPATH = "//button[@class='btn btn_primary btn_small btn_inventory ']";
    public static final String REMOVE_FROM_CART_MAIN_XPATH = "//button[@class='btn btn_secondary btn_small btn_inventory ']";
    public static final String REMOVE_FROM_CART_XPATH = "//button[@class='btn btn_secondary btn_small cart_button']";
    public static final String CART_LINK_CHILD_XPATH = "//a[@class='shopping_cart_link']//*";
    public static final String CART_WIDGET_XPATH = "//span[@class='shopping_cart_badge']";
    public static final String CONTINUE_SHOPPING_ID = "continue-shopping";
    public static final String ITEM_IMAGE_HREF_ID = "item_%d_img_link";
}
