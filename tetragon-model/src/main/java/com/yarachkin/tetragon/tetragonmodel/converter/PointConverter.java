package com.yarachkin.tetragon.tetragonmodel.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;

public class PointConverter {

    public static Point convert(PointDto pointDto) {
        // TODO: 09.10.2017 Check String for double value
        double x = Double.parseDouble(pointDto.getX());
        double y = Double.parseDouble(pointDto.getY());

        return new Point(x, y);

    }
}
