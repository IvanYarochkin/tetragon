package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointValidator {
    private static final String DOUBLE_PATTERN = "-?\\d{1,6}.?\\d{0,6}";

    private PointValidator() {

    }

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

        return matcher.matches();
    }
}
