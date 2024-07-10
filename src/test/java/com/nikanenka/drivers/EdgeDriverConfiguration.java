package com.nikanenka.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverConfiguration extends DriverConfiguration {
    public EdgeDriver getConfigureEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions opt = new EdgeOptions();
        opt.addArguments(getCommonOptions());
        return new EdgeDriver(opt);
    }
}
