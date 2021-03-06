package com.yarachkin.tetragon.tetragonmodel.factory.impl;

import com.yarachkin.tetragon.tetragonmodel.entity.AbstractEntity;
import com.yarachkin.tetragon.tetragonmodel.exception.ModelTetragonException;
import com.yarachkin.tetragon.tetragonmodel.factory.Factory;
import com.yarachkin.tetragon.tetragonmodel.factory.FactoryEntityType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FactoryImpl implements Factory {

    @Override
    public AbstractEntity factoryMethod(String objectName) throws ModelTetragonException {
        String objectNameUpperCase = objectName.toUpperCase();

        if (!Arrays.asList(FactoryEntityType.values()).stream()
                .map(Object::toString)
                .collect(Collectors.toList()).contains(objectNameUpperCase)) {
            throw new ModelTetragonException("Incorrect value to create object. Value = " + objectName);
        }
        return FactoryEntityType.valueOf(objectNameUpperCase.toUpperCase()).getEntity();
    }
}
