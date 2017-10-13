package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

public class CheckingTetragonAction {

    private CheckingTetragonAction(){

    }

    public static boolean isConvex(Tetragon tetragon) {
        if (CalculatingTriangleAction.calculateAreaAction(tetragon.getFirst(), tetragon.getSecond(), tetragon.getThird())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        if (CalculatingTriangleAction.calculateAreaAction(tetragon.getFirst(), tetragon.getThird(), tetragon.getFourth())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        if (CalculatingTriangleAction.calculateAreaAction(tetragon.getSecond(), tetragon.getThird(), tetragon.getFourth())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        return CalculatingTriangleAction.calculateAreaAction(tetragon.getFirst(), tetragon.getSecond(), tetragon.getFourth())
                < CalculatingTetragonAction.calculateArea(tetragon);
    }
}
