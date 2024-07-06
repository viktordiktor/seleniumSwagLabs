package com.nikonenko.util;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@UtilityClass
public class TestUtil {
    public static ChromeDriver getConfigureChromeDriver() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        opt.addArguments("--no-sandbox");
        opt.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(opt);
    }
}
