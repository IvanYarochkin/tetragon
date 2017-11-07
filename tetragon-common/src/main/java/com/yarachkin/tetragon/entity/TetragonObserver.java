package com.yarachkin.tetragon.entity;

public interface TetragonObserver {
    double update(Tetragon tetragon);

    double getValue();
}
