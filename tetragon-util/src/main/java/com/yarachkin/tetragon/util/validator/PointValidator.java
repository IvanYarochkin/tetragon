package com.yarachkin.tetragon.util.validator;

import com.yarachkin.tetragon.model.dto.PointDto;
import com.yarachkin.tetragon.util.common.StringUtil;

public class PointValidator {

    private PointValidator() {

    }

    public static boolean validate(PointDto pointDto) {
        if (pointDto == null) {
            return false;
        }

        if (!StringUtil.isDoubleValue(pointDto.getX())) {
            return false;
        }

        return StringUtil.isDoubleValue(pointDto.getY());
    }


}
