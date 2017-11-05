package com.yarachkin.tetragon.factory;

import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.exception.ModelTetragonException;

public interface Factory {
    AbstractEntity factoryMethod(String objectName) throws ModelTetragonException;
}
