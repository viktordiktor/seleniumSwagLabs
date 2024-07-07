package com.nikonenko.drivers;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;

@UtilityClass
public class DriverFactory {
    public static WebDriver getConfiguredDriver() {
        switch (System.getProperty("browser", "CHROME")) {
            case "CHROME" -> {
                return new ChromeDriverConfiguration().getConfigureChromeDriver();
            }
            case "FIREFOX" -> {
                return new FirefoxDriverConfiguration().getConfigureFirefoxDriver();
            }
            case "EDGE" -> {
                return new EdgeDriverConfiguration().getConfigureEdgeDriver();
            }
        }
        throw new RuntimeException("Unsupported browser: " + System.getProperty("browser"));
    }
}
