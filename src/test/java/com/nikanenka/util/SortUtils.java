package com.nikanenka.util;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@UtilityClass
public class SortUtils {
    public static boolean checkCorrectNameAsc(List<WebElement> elements) {
        return IntStream.range(0, elements.size() - 1)
                .allMatch(i -> elements.get(i).getText().compareTo(elements.get(i + 1).getText()) <= 0);
    }

    public static boolean checkCorrectNameDesc(List<WebElement> elements) {
        return IntStream.range(0, elements.size() - 1)
                .allMatch(i -> elements.get(i).getText().compareTo(elements.get(i + 1).getText()) >= 0);
    }

    public static boolean checkCorrectPriceAsc(List<WebElement> elements) {
        return IntStream.range(0, elements.size() - 1)
                .allMatch(i -> {
                    double value1 = extractDoubleValue(elements.get(i).getText());
                    double value2 = extractDoubleValue(elements.get(i + 1).getText());
                    return value1 <= value2;
                });
    }

    public static boolean checkCorrectPriceDesc(List<WebElement> elements) {
        return IntStream.range(0, elements.size() - 1)
                .allMatch(i -> {
                    double value1 = extractDoubleValue(elements.get(i).getText());
                    double value2 = extractDoubleValue(elements.get(i + 1).getText());
                    return value1 >= value2;
                });
    }

    private static double extractDoubleValue(String text) {
        Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String valueString = matcher.group(1);
            return Double.parseDouble(valueString);
        }
        throw new IllegalArgumentException(FailUtil.ILLEGAL_ARGUMENT + text);
    }
}
