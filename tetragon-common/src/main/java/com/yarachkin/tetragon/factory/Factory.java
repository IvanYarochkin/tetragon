package com.yarachkin.tetragon.factory;

import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.exception.CommonTetragonException;

public interface Factory {
    AbstractEntity factoryMethod(String objectName) throws CommonTetragonException;
}
