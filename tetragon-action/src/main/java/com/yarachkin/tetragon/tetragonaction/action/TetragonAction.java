package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

public class TetragonAction {
    private TetragonAction() {

    }

    public static double calculatePerimeter(Tetragon tetragon) {
        double firstDistance = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondDistance = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdDistance = calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthDistance = calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        return firstDistance + secondDistance + thirdDistance + fourthDistance;
    }

    public static double calculateArea(Tetragon tetragon) {
        double firstDistance = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondDistance = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdDistance = calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthDistance = calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        double halfPerimeter = (firstDistance + secondDistance + thirdDistance + fourthDistance) / 2;

        return Math.sqrt((halfPerimeter - firstDistance) * (halfPerimeter - secondDistance)
                * (halfPerimeter - thirdDistance) * (halfPerimeter - fourthDistance));
    }

    private static double calculateDistanceBetweenPoints(Point first, Point second) {
        double oxDistant = first.getX() - second.getX();
        double oyDistant = first.getY() - second.getY();

        return Math.hypot(oxDistant, oyDistant);
    }
}
