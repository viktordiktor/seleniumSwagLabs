package com.nikanenka.drivers;

import java.util.ArrayList;
import java.util.List;

public class DriverConfiguration {
    protected List<String> getCommonOptions() {
        List<String> options = new ArrayList<>();
        options.add("--headless");
        options.add("--no-sandbox");
        options.add("--test-type");
        options.add("--disable-dev-shm-usage");
        return options;
    }
}
