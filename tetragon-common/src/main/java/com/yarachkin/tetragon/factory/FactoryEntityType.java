package com.yarachkin.tetragon.factory;

import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;

public enum FactoryEntityType {
    TETRAGON {
        @Override
        public AbstractEntity getEntity() {
            return new Tetragon();
        }
    }, POINT {
        @Override
        public AbstractEntity getEntity() {
            return new Point();
        }
    };

    public abstract AbstractEntity getEntity();
}
