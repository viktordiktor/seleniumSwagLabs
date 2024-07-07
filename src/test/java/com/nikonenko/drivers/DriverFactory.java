package com.nikonenko.drivers;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

@UtilityClass
@Log4j
public class DriverFactory {
    public static WebDriver getConfiguredDriver() {
        log.info(System.getenv("BROWSER"));
        switch (System.getenv("BROWSER")) {
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
        throw new RuntimeException("Unsupported browser: " + System.getenv("BROWSER"));
    }
}
