package com.nikanenka.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverConfiguration extends DriverConfiguration {
    public FirefoxDriver getConfigureFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opt = new FirefoxOptions();
        opt.addArguments(getCommonOptions());
        return new FirefoxDriver(opt);
    }
}
