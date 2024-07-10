package com.nikanenka.drivers;

import com.nikanenka.util.DriverUtil;
import com.nikanenka.util.LogUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

@UtilityClass
@Log4j
public class DriverFactory {
    public static WebDriver getConfiguredDriver() {
        String browserEnv = System.getenv(DriverUtil.BROWSER);
        if (browserEnv == null) {
            log.warn(LogUtil.CONFIGURATION_NOT_FOUND);
            browserEnv = DriverUtil.CHROME;
        }
        return getDriverByEnv(browserEnv);
    }

    private WebDriver getDriverByEnv(String browserEnv) {
        switch (browserEnv) {
            case DriverUtil.CHROME -> {
                return new ChromeDriverConfiguration().getConfigureChromeDriver();
            }
            case DriverUtil.FIREFOX -> {
                return new FirefoxDriverConfiguration().getConfigureFirefoxDriver();
            }
            case DriverUtil.EDGE -> {
                return new EdgeDriverConfiguration().getConfigureEdgeDriver();
            }
            default -> {
                log.warn(LogUtil.UNSUPPORTED_BROWSER + browserEnv);
                return new ChromeDriverConfiguration().getConfigureChromeDriver();
            }
        }
    }
}
