package com.yarachkin.tetragon.tetragonmodel.factory;

import com.yarachkin.tetragon.tetragonmodel.entity.AbstractEntity;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

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
