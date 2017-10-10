package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointValidator {
    private static final String DOUBLE_PATTERN = "-?[0-9]+.?[0-9]*";

    public static boolean isDoubleValues(PointDto pointDto) {
        if (pointDto == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(DOUBLE_PATTERN);
        Matcher matcher = pattern.matcher(pointDto.getX());
        if (!matcher.matches()) {
            return false;
        }

        matcher = pattern.matcher(pointDto.getY());
        if (!matcher.matches()) {
            return false;
        }

        return true;
    }
}
