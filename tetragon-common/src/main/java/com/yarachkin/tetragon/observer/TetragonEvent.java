package com.yarachkin.tetragon.observer;

import com.yarachkin.tetragon.entity.Tetragon;

import java.util.EventObject;

public class TetragonEvent extends EventObject {

    public TetragonEvent(Tetragon source) {
        super(source);
    }

    @Override
    public Tetragon getSource() {
        return (Tetragon) super.getSource();
    }
}
