package com.yarachkin.tetragon.entity;

import com.yarachkin.tetragon.action.CalculatingTetragonAction;

public class Square implements TetragonObserver {
    private double square;

    @Override
    public double update(Tetragon tetragon) {
        return square = CalculatingTetragonAction.calculateArea(tetragon);
    }

    @Override
    public double getValue() {
        return square;
    }

    @Override
    public String toString() {
        return "Square = " + square + ";";
    }
}
