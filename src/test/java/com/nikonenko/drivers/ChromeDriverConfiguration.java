package com.nikonenko.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverConfiguration extends DriverConfiguration {
    public ChromeDriver getConfigureChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments(getCommonOptions());
        return new ChromeDriver(opt);
    }
}