package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonutil.common.StringUtil;

public final class PointValidator {

    private PointValidator() {

    }

    public static boolean validate(PointDto pointDto) {
        if ( pointDto == null ) {
            return false;
        }

        if ( !StringUtil.isDoubleValue(pointDto.getX()) ) {
            return false;
        }

        return StringUtil.isDoubleValue(pointDto.getY());
    }
}
