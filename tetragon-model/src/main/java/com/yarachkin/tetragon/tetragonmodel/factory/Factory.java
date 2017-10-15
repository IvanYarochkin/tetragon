package com.yarachkin.tetragon.tetragonmodel.factory;

import com.yarachkin.tetragon.tetragonmodel.entity.AbstractEntity;
import com.yarachkin.tetragon.tetragonmodel.exception.ModelTetragonException;

public interface Factory {
    AbstractEntity factoryMethod(String objectName) throws ModelTetragonException;
}
