package com.yarachkin.tetragon.tetragonutil.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

    private static final Pattern DOUBLE_PATTERN = Pattern.compile("-?\\d{1,6}.?\\d{0,6}");

    private StringUtil() {

    }

    public static boolean isDoubleValue(String value) {
        Matcher matcher = DOUBLE_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return false;
        }

        return true;
    }
}
