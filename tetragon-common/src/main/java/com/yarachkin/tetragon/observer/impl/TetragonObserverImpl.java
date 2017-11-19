package com.yarachkin.tetragon.observer.impl;

import com.yarachkin.tetragon.action.CalculatingTetragonAction;
import com.yarachkin.tetragon.observer.AdditionalTetragonData;
import com.yarachkin.tetragon.observer.TetragonEvent;
import com.yarachkin.tetragon.observer.TetragonObserver;

public class TetragonObserverImpl implements TetragonObserver {

    private AdditionalTetragonData data;

    public TetragonObserverImpl(AdditionalTetragonData data) {
        this.data = data;
    }

    @Override
    public void update(TetragonEvent event) {
        if ( event.getSource() != null ) {
            data.setArea(CalculatingTetragonAction.calculateArea(event.getSource()));
            data.setPerimeter(CalculatingTetragonAction.calculatePerimeter(event.getSource()));
        }
    }
}
