package com.yarachkin.tetragon.entity;

import com.yarachkin.tetragon.action.CalculatingTetragonAction;

public class Perimeter implements TetragonObserver {
    private double perimeter;

    @Override
    public double update(Tetragon tetragon) {
        return perimeter = CalculatingTetragonAction.calculatePerimeter(tetragon);
    }

    @Override
    public double getValue() {
        return perimeter;
    }

    @Override
    public String toString() {
        return "Perimeter = " + perimeter + ";";
    }
}
